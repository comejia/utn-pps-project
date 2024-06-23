package flow.utils;

import ch.qos.logback.classic.Logger;
import flow.stepDefinitions.Hooks;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.apache.commons.lang3.SystemUtils;
import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class UtilsMedia {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(UtilsMedia.class));

    public static ScreenRecorder screenRecorder;

    public static String getApplicationPath() {
        String path;
        if (SystemUtils.IS_OS_MAC) {
            path = "/usr/local/bin/";
        } else if (SystemUtils.IS_OS_LINUX) {
            path = "/usr/bin/";
        } else if (SystemUtils.IS_OS_WINDOWS) {
            // TODO
            path = "windows";
        } else {
            // TODO
            path = "unknown";
        }
        return path;
    }

    public static void convertVideoToMp4(String videoIn,
                                         String videoOut,
                                         String videoCodec) throws IOException {
        try {
            FFmpeg ffmpeg = new FFmpeg(getApplicationPath() + "ffmpeg");
            FFprobe ffprobe = new FFprobe(getApplicationPath() + "ffprobe");

            FFmpegBuilder builder = new FFmpegBuilder()

                    .setInput(videoIn)     // Filename, or a FFmpegProbeResult
                    .overrideOutputFiles(true) // Override the output if it exists

                    .addOutput(videoOut)   // Filename for the destination
                    //.setFormat("mp4")        // Format is inferred from filename, or can be set
                    //.setTargetSize(250_000)  // Aim for a 250KB file

                    //.disableSubtitle()       // No subtiles

                    //.setAudioChannels(1)         // Mono audio
                    //.setAudioCodec("aac")        // using the aac codec
                    //.setAudioSampleRate(48_000)  // at 48KHz
                    //.setAudioBitRate(32768)      // at 32 kbit/s

                    .setVideoCodec(videoCodec)     // Video using x264
                    //.setVideoFrameRate(frameRate, 1)     // at 24 frames per second
                    //.setVideoResolution(sizeWidth, sizeHeight) // at 640x480 resolution

                    .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
                    .done();

            FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

            // Run a one-pass encode
            executor.createJob(builder).run();

            // Or run a two-pass encode (which is better quality at the cost of being slower)
            //executor.createTwoPassJob(builder).run();

            logger.info("Converted media file " + videoIn + " to " + videoOut);

            // Remove input file
            File file = new File(videoIn);
            logger.info("ConvertVideoToMp4 -> Name: " + file.getName() + " AbsolutePath: " + file.getAbsolutePath());
            if (file.delete()) {
                logger.info("File " + videoIn + " deleted successfully");
            } else {
                logger.info("File " + videoIn + " could not be deleted");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void startScreenRecording() throws IOException, AWTException {
        try {
            if (Hooks.props.videoLog() && !Hooks.props.browserHeadless() && Hooks.props.cloudService() == null
                    || Hooks.props.cloudService().isEmpty()) {
                File directory = new File(UtilsOs.getFilePath(null));
                if (!directory.exists()) {
                    directory.mkdirs();
                    logger.info(directory + " directory created");
                }

                //Create a instance of GraphicsConfiguration to get the Graphics configuration
                //of the Screen. This is needed for ScreenRecorder class.
                GraphicsConfiguration gc = GraphicsEnvironment
                        .getLocalGraphicsEnvironment()
                        .getDefaultScreenDevice()
                        .getDefaultConfiguration();

                //Create a instance of ScreenRecorder with the required configurations
                screenRecorder = new ScreenRecorder(gc,
                        gc.getBounds(),
                        new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                        new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                                CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                                DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                                QualityKey, 1.0f,
                                KeyFrameIntervalKey, 15 * 60),
                        new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                        null,
                        new File(UtilsOs.getFilePath(null)));

                screenRecorder.start();
                logger.info("Started test recording");
            }
        } catch (HeadlessException e) {
            logger.warn("Recording not starting because of headless run");
        }
    }

    public static void cleanupRecording() {
        try {
            if (Hooks.props.videoLog() && !Hooks.props.browserHeadless() && Hooks.props.cloudService() == null
                    || Hooks.props.cloudService().isEmpty()) {
                // Stop recording
                try {
                    screenRecorder.stop();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    // Remove video files
                    List<File> createdMovieFiles = screenRecorder.getCreatedMovieFiles();
                    for (File movie : createdMovieFiles) {
                        movie.delete();
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                logger.info("Stopped and cleaned test recording");
            }
        } catch (HeadlessException e) {
            logger.warn("Recording not started because of headless run");
        }
    }
}
