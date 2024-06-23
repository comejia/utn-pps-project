package listeners;

import ch.qos.logback.classic.Logger;
import flow.stepDefinitions.Hooks;
import flow.utils.UtilsFile;
import flow.utils.UtilsMedia;
import io.qameta.allure.Attachment;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import net.lightbody.bmp.core.har.Har;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.json.JsonException;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StepReporterEventListener implements StepLifecycleListener {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(StepReporterEventListener.class));

    @Override
    public void beforeStepStart(StepResult result) {
        logger.info("Step to be Completed: " + result.getName());
    }

    @Override
    public void afterStepUpdate(StepResult result) {
        logger.info("Step Completed: " + result.getName());
        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {

            logger.info("Attached logs after failed or broken step");
            String filePath = System.getProperty("user.dir") + "/target/log/";
            File directory = new File(filePath);
            UtilsFile.createDirIfNotExists(directory);
            String fileName = filePath + "step_error_log";
            if (!Hooks.alreadyFailed) {
                Hooks.props.setProperty("stepBroken", result.getName());
                //Hooks.stepBroken = result.getName();
                if (Hooks.props.errorLog()) {
                    takeScreenShot();
                    saveWebBrowserLog();
                    savePageSource();
                }
                if (Hooks.props.videoLog() && !Hooks.props.browserHeadless()) {
                    var video = saveVideo(fileName, ".mp4");
                    Hooks.props.setProperty("fileNameVideo", fileName + ".mp4");
                    try {
                        FileUtils.writeByteArrayToFile(new File(Hooks.props.fileNameVideo()), video);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (Hooks.props.browserProxy()) {
                    saveProxyLog(fileName);
                }
                if (Hooks.props.browserNetworkLogs()) {
                    this.saveBrowserNetworkLog();
                }
                // Send test execution status to BrowserStack
                if (Hooks.props.cloudService().equals("browserstack")) {
                    Hooks.getFailedStep().set(result.getName());
                }
            }
            // Set alreadyFailed variable so it don't try to delete file again
            // Hooks.alreadyFailed = true;
        }
    }

    @Override
    public void afterStepStop(StepResult result) {
        Hooks.stepName.set(result.getName());
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = ".png")
    public byte[] takeScreenShot() {
        byte[] screenShot = new byte[0];
        try {
            screenShot = ((TakesScreenshot) Hooks.getDriver().getAugmentedDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException e) {
            logger.error("Error while taking screenshot");
            e.printStackTrace();
        }
        return screenShot;
    }

    @Attachment(value = "Browser log", type = "text/plain", fileExtension = ".txt")
    public byte[] saveWebBrowserLog() {
        byte[] logs = new byte[0];
        try {
            List<LogEntry> logEntries = Hooks.getDriver().manage().logs().get(LogType.BROWSER).getAll();
            logs = this.saveLog(logEntries);
        } catch (JsonException e) {
            logger.error("Network log is not available for " + Hooks.props.browser());
            e.printStackTrace();
        }
        return logs;
    }

    @Attachment(value = "Network log", type = "text/plain", fileExtension = ".txt")
    public byte[] saveBrowserNetworkLog() {
        byte[] logs = new byte[0];
        try {
            var logEntries = Hooks.getDriver().manage().logs().get(LogType.PERFORMANCE).toJson();
            logs = this.saveLog(logEntries);
        } catch (JsonException e) {
            logger.error("Network log is not available for " + Hooks.props.browser());
            e.printStackTrace();
        }
        return logs;
    }

    @Attachment(value = "Screen recorder", type = "video/mp4", fileExtension = ".mp4")
    public byte[] saveVideo(String videoCaptureURL, String extension) {
        byte[] video = new byte[0];
        try {
            stopScreenRecording(videoCaptureURL);
            UtilsMedia.convertVideoToMp4(
                    videoCaptureURL + ".avi",
                    videoCaptureURL + ".mp4",
                    "libx264"
            );
            video = Files.readAllBytes(Paths.get(videoCaptureURL + extension));
        } catch (IOException e) {
            logger.error("Failed fetching video from: " + videoCaptureURL);
            e.printStackTrace();
        }
        return video;
    }

    @Attachment(value = "Proxy log", type = "text/plain", fileExtension = ".log")
    public byte[] saveProxyLog(String filename) {
        try {
            Har browserProxyLogFilename = Hooks.getProxyServer().getHar();
            browserProxyLogFilename.writeTo(new File(filename + "_proxy.log"));
            return Files.readAllBytes(Paths.get(filename + "_proxy.log"));
        } catch (IOException e) {
            logger.error("Failed fetching proxy log from: " + filename + "_proxy.log");
            e.printStackTrace();
        }
        return new byte[0];
    }

    private byte[] saveLog(List<LogEntry> logEntries) {
        ByteArrayOutputStream logOutputStream = new ByteArrayOutputStream();
        for (LogEntry entry : logEntries) {
            try {
                logOutputStream.write(String.format("%s\n", entry).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return logOutputStream.toByteArray();
    }

    private void stopScreenRecording(String filename) {
        String file = filename + ".avi";

        try {
            UtilsMedia.screenRecorder.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<File> createdMovieFiles = UtilsMedia.screenRecorder.getCreatedMovieFiles();
            for (File movie : createdMovieFiles) {
                Files.move(Path.of(movie.getAbsolutePath()),
                        Path.of(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment(value = "page source", type = "text/plain", fileExtension = ".xml")
    public String savePageSource() {
        return Hooks.getDriver().getPageSource();
    }

    public void setBrowserStackTestStatus(String status, String reason) {
        JavascriptExecutor jse = (JavascriptExecutor) Hooks.getDriver();
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"" + status + "\", \"reason\": \"" + reason + "\"}}");
        logger.info("Set BrowserStack status as " + status);
    }
}