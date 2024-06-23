package flow.utils;

import ch.qos.logback.classic.Logger;
import flow.stepDefinitions.Hooks;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import net.lightbody.bmp.util.HttpObjectUtil;
import org.awaitility.Awaitility;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.Proxy;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

public class UtilsProxy {

    private static Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(Hooks.class));


    public static BrowserMobProxy getProxyServer() {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true); // Needed for application with invalid certificates
        proxy.setHarCaptureTypes(CaptureType.REQUEST_CONTENT,
                CaptureType.REQUEST_BINARY_CONTENT,
                CaptureType.REQUEST_HEADERS,
                CaptureType.REQUEST_COOKIES,
                CaptureType.RESPONSE_CONTENT,
                CaptureType.RESPONSE_BINARY_CONTENT,
                CaptureType.RESPONSE_HEADERS,
                CaptureType.RESPONSE_COOKIES);
        proxy.enableHarCaptureTypes(
                CaptureType.REQUEST_CONTENT,
                CaptureType.REQUEST_BINARY_CONTENT,
                CaptureType.REQUEST_HEADERS,
                CaptureType.REQUEST_COOKIES,
                CaptureType.RESPONSE_CONTENT,
                CaptureType.RESPONSE_BINARY_CONTENT,
                CaptureType.RESPONSE_HEADERS,
                CaptureType.RESPONSE_COOKIES);
        proxy.start();

        return proxy;
    }

    public static BrowserMobProxy getProxyServer(int port) {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true); // Needed for application with invalid certificates
        proxy.setHarCaptureTypes(CaptureType.REQUEST_CONTENT,
                CaptureType.REQUEST_BINARY_CONTENT,
                CaptureType.REQUEST_HEADERS,
                CaptureType.REQUEST_COOKIES,
                CaptureType.RESPONSE_CONTENT,
                CaptureType.RESPONSE_BINARY_CONTENT,
                CaptureType.RESPONSE_HEADERS,
                CaptureType.RESPONSE_COOKIES);
        proxy.enableHarCaptureTypes(
                CaptureType.REQUEST_CONTENT,
                CaptureType.REQUEST_BINARY_CONTENT,
                CaptureType.REQUEST_HEADERS,
                CaptureType.REQUEST_COOKIES,
                CaptureType.RESPONSE_CONTENT,
                CaptureType.RESPONSE_BINARY_CONTENT,
                CaptureType.RESPONSE_HEADERS,
                CaptureType.RESPONSE_COOKIES);
        proxy.start(port);

        return proxy;
    }

    public static Proxy getSeleniumProxy(BrowserMobProxy proxyServer) {
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyServer);
        try {
            String hostIp = Inet4Address.getLocalHost().getHostAddress();
            seleniumProxy.setHttpProxy(hostIp + ":" + proxyServer.getPort());
            seleniumProxy.setSslProxy(hostIp + ":" + proxyServer.getPort());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Assert.fail("invalid Host Address");
        }
        return seleniumProxy;
    }

    public static void setProxyCountry() {
        if (Hooks.props.country() != null && !Hooks.props.country().equals("ar") && Hooks.props.browserProxy()) {
            String countryString = Hooks.props.country().toUpperCase();

            // Start proxy server if not started
            try {
                if (Hooks.proxyServer.isStarted()) {
                    logger.info("Proxy is already started");
                }
            } catch (NullPointerException e) {
                Hooks.proxyServer = getProxyServer();
                logger.info("Started proxy for " + countryString + " country");
            }

            // Response body will be specified country in System variable parameter in uppercase
            String uri = "/geo/v1/country";

            addRequestFilter(countryString, uri);
        }
    }

    public static void addRequestFilter(String stubResponse, String uriFilter) {
        Hooks.proxyServer.addRequestFilter((request, contents, messageInfo) -> {

            if (request.getUri().contains(uriFilter)) {

                //Use DefaultFullHttpResponse for posting the json body
                HttpResponse httpResponse = new DefaultFullHttpResponse(request.getProtocolVersion(), HttpResponseStatus.OK);
                //Close the connection so it doesn't pass through
                httpResponse.headers().add("CONNECTION", "Close");
                //Specify the content-type and charset
                httpResponse.headers().add("Content-Type", "application/json; charset=UTF-8");
                //other headers if necessary
                httpResponse.headers().add("Access-Control-Allow-Headers", "");
                httpResponse.headers().add("Access-Control-Allow-Origin", "*");
                httpResponse.headers().add("Access-Control-Expose-Headers", "");

                //replace the body
                HttpObjectUtil.replaceTextHttpEntityBody((FullHttpMessage) httpResponse, stubResponse);

                return httpResponse;
            }

            return null;
        });
        logger.info("Added request filter for '" + uriFilter + "': " + stubResponse);
    }

    public static Har getProxyEntries(BrowserMobProxy proxy) {
        return proxy.getHar();
    }

    public static void stopProxyServer(BrowserMobProxy proxy) {
        if (proxy != null) {
            try {
                proxy.endHar();
                proxy.stop();
                logger.info("Proxy stopped successfully");
            } catch (NullPointerException e) {
                logger.info("Proxy was not started");
            }
        }
    }

    public static void assertProxyEntry(BrowserMobProxy proxy,
                                        String type,
                                        String dl,
                                        String dp,
                                        String ec,
                                        String ea,
                                        String el,
                                        int expectedAmount) {
        Awaitility.await().atMost(10, SECONDS).until(() -> {
            // Check favorite content
            try {
                int foundAmount = 0;
                List<HarEntry> entries = proxy.getHar().getLog().getEntries();
                for (HarEntry entry : entries) {

                    if (entry.getRequest().getQueryString().toString().contains("t=" + type) &&
                            entry.getRequest().getQueryString().toString().contains("dl=" + dl) &&
                            entry.getRequest().getQueryString().toString().contains("dp=" + dp) &&
                            entry.getRequest().getQueryString().toString().contains("ec=" + ec) &&
                            entry.getRequest().getQueryString().toString().contains("ea=" + ea) &&
                            entry.getRequest().getQueryString().toString().contains("el=" + el)) {
                        logger.info("Proxy entry match: t=" + type + " dl=" + dl + " dp=" + dp
                                + " ec=" + ec + " ea=" + ea + " el=" + el);
                        printEntryRequestData(entry, true, true, true, false, true);
                        foundAmount++;
                    }
                }
                assertEquals("Proxy entry amount incorrect: t=" + type + " dl=" + dl + " dp=" + dp
                        + " ec=" + ec + " ea=" + ea + " el=" + el, expectedAmount, foundAmount);
                logger.info("Proxy entry amount: " + foundAmount);
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                logger.info("Proxy complete list: ");
                List<HarEntry> entries = proxy.getHar().getLog().getEntries();
                for (HarEntry entry : entries) {
                    printEntryRequestData(entry, true, true, true, false, true);
                }
                return false;
            }
        });
    }

    public static void assertProxyEntryAllowNulls(BrowserMobProxy proxy,
                                                  String type,
                                                  String dl,
                                                  String dp,
                                                  String ec,
                                                  String ea,
                                                  String el,
                                                  int expectedAmount) {
        Awaitility.await().atMost(3, SECONDS).until(() -> {
            // Check favorite content
            try {
                int foundAmount = 0;
                // Iterate all Proxy entries
                List<HarEntry> entries = proxy.getHar().getLog().getEntries();
                for (HarEntry entry : entries) {
                    if (type != null) {
                        if (!entry.getRequest().getQueryString().toString().contains("t=" + type)) {
                            continue;
                        }
                    }
                    if (dl != null) {
                        if (!entry.getRequest().getQueryString().toString().contains("dl=" + dl)) {
                            continue;
                        }
                    }
                    if (dp != null) {
                        if (!entry.getRequest().getQueryString().toString().contains("dp=" + dp)) {
                            continue;
                        }
                    }
                    if (ec != null) {
                        if (!entry.getRequest().getQueryString().toString().contains("ec=" + ec)) {
                            continue;
                        }
                    }
                    if (ea != null) {
                        if (!entry.getRequest().getQueryString().toString().contains("ea=" + ea)) {
                            continue;
                        }
                    }
                    if (el != null) {
                        if (!entry.getRequest().getQueryString().toString().contains("el=" + el)) {
                            continue;
                        }
                    }
                    logger.info("Proxy entry match: t=" + type + " dl=" + dl + " dp=" + dp
                            + " ec=" + ec + " ea=" + ea + " el=" + el);
                    printEntryRequestData(entry, true, true, true, false, true);
                    foundAmount++;
                }
                assertEquals("Proxy entry amount incorrect: t=" + type + " dl=" + dl + " dp=" + dp
                        + " ec=" + ec + " ea=" + ea + " el=" + el, expectedAmount, foundAmount);
                logger.info("Proxy entry amount: " + foundAmount);
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                logger.info("Proxy complete list: ");
                List<HarEntry> entries = proxy.getHar().getLog().getEntries();
                for (HarEntry entry : entries) {
                    printEntryRequestData(entry, true, true, true, true, true);
                }
                return false;
            }
        });
    }

    public static HarEntry getProxyEntryHarEntry(BrowserMobProxy proxy,
                                                 String type,
                                                 String dl,
                                                 String dp,
                                                 String ec,
                                                 String ea,
                                                 String el) {
        HarEntry entryQueryParams = null;
        try {
            // Iterate all Proxy entries
            List<HarEntry> entries = proxy.getHar().getLog().getEntries();
            for (HarEntry entry : entries) {
                if (type != null) {
                    if (!entry.getRequest().getQueryString().toString().contains("t=" + type)) {
                        continue;
                    }
                }
                if (dl != null) {
                    if (!entry.getRequest().getQueryString().toString().contains("dl=" + dl)) {
                        continue;
                    }
                }
                if (dp != null) {
                    if (!entry.getRequest().getQueryString().toString().contains("dp=" + dp)) {
                        continue;
                    }
                }
                if (ec != null) {
                    if (!entry.getRequest().getQueryString().toString().contains("ec=" + ec)) {
                        continue;
                    }
                }
                if (ea != null) {
                    if (!entry.getRequest().getQueryString().toString().contains("ea=" + ea)) {
                        continue;
                    }
                }
                if (el != null) {
                    if (!entry.getRequest().getQueryString().toString().contains("el=" + el)) {
                        continue;
                    }
                }
                logger.info("Proxy entry match: t=" + type + " dl=" + dl + " dp=" + dp +
                        " ec=" + ec + " ea=" + ea + " el=" + el);
                printEntryRequestData(entry, true, true, true, true, true);
                // set entryFound variable
                entryQueryParams = entry;
            }
        } catch (AssertionError e) {
            logger.info("Proxy complete list: ");
            List<HarEntry> entries = proxy.getHar().getLog().getEntries();
            for (HarEntry entry : entries) {
                printEntryRequestData(entry, true, true, true, true, true);
            }
            throw e;
        }
        return entryQueryParams;
    }

    public static void logProxyEntriesHarEntries() {
        List<HarEntry> entries = Hooks.proxyServer.getHar().getLog().getEntries();
        for (HarEntry entry : entries) {
            printEntryRequestData(entry, true, true, true, true, true);
        }
    }

    public static void logProxyEntriesHarEntriesFiltered(BrowserMobProxy proxy, String filterString) {
        List<HarEntry> entries = proxy.getHar().getLog().getEntries();
        for (HarEntry entry : entries) {
            if (filterString != null) {
                if (entry.getRequest().getQueryString().toString().contains(filterString)
                        || entry.getRequest().getUrl().contains(filterString)) {
                    printEntryRequestData(entry, true, true, true, true, true);
                }
            }
        }
    }

    public static void assertProxyEntriesHarEntriesFilteredPostData(BrowserMobProxy proxy,
                                                                    String filterString,
                                                                    String eventType,
                                                                    int eventTypeAmount) {
        List<HarEntry> entries = proxy.getHar().getLog().getEntries();
        for (HarEntry entry : entries) {
            if (filterString != null) {
                if (entry.getRequest().getQueryString().toString().contains(filterString)
                        || entry.getRequest().getUrl().contains(filterString)) {
                    int foundAmount = 0;

                    printEntryRequestData(entry, true, true, true, true, true);

                    //JSONObject entryRequestPostDataJson = this.utilsJson.getJsonFromString(entryRequestPostDataString);
                    try {
                        String entryRequestPostDataString = entry.getRequest().getPostData().getText();

                        JSONObject entryRequestPostDataJson = new JSONObject(entryRequestPostDataString);

                        JSONArray eventsArray = entryRequestPostDataJson.getJSONArray("events");
                        for (int i = 0; i < eventsArray.length(); i++) {
                            JSONObject object = eventsArray.getJSONObject(i);
                            if (object.getString("eventType").equals(eventType)) {
                                foundAmount++;
                                logger.info("Proxy entry eventType matches: " + object.getString("eventType"));
                            }
                        }

                        assertTrue("Proxy entry eventType amount is not as expected", eventTypeAmount <= foundAmount);
                        logger.info("Proxy entries found (" + foundAmount
                                + ") matches minimum expected amount (" + eventTypeAmount + ")");
                    } catch (NullPointerException e) {
                        logger.info("Proxy entry PostData not found");
                    }
                }
            }
        }
    }

    public static String getHarEntryRequestQueryStringValue(HarEntry entry, String queryStringName) {
        String queryStringValue = null;
        int entryAmount = entry.getRequest().getQueryString().size();
        for (int i = 0; i < entryAmount; i++) {
            String requestQueryStringName = entry.getRequest().getQueryString().get(i).getName();
            if (requestQueryStringName.equals(queryStringName)) {
                queryStringValue = entry.getRequest().getQueryString().get(i).getValue();
            }
        }
        assertNotNull("QueryString name '" + queryStringName + "' not found", queryStringValue);
        logger.info("QueryString '" + queryStringName + ":' " + queryStringValue);
        return queryStringValue;
    }

    public static String getStringProxyEntriesHarEntryQueryStringValue(BrowserMobProxy proxy,
                                                                       String type,
                                                                       String dl,
                                                                       String dp,
                                                                       String ec,
                                                                       String ea,
                                                                       String el,
                                                                       String queryStringName) {
        HarEntry harEntry = getProxyEntryHarEntry(proxy, type, dl, dp, ec, ea, el);
        String queryStringValue = getHarEntryRequestQueryStringValue(harEntry, queryStringName);
        logger.info(queryStringName + " value: " + queryStringValue);
        return queryStringValue;
    }

    public static void printEntryRequestData(HarEntry entry,
                                             boolean url,
                                             boolean queryString,
                                             boolean headers,
                                             boolean postDataText,
                                             boolean cookies) {
        if (url) {
            try {
                logger.info("Proxy entry Url: " + entry.getRequest().getUrl());
            } catch (NullPointerException e) {
                logger.info("Proxy entry Url not found");
            }
        }
        if (queryString) {
            try {
                logger.info("Proxy entry QueryString: " + entry.getRequest().getQueryString());
            } catch (NullPointerException e) {
                logger.info("Proxy entry QueryString not found");
            }
        }
        if (headers) {
            try {
                logger.info("Proxy entry Headers: " + entry.getRequest().getHeaders());
            } catch (NullPointerException e) {
                logger.info("Proxy entry Headers not found");
            }
        }
        if (postDataText) {
            try {
                logger.info("Proxy entry PostData Text: " +
                        UtilsJson.prettyJsonString(entry.getRequest().getPostData().getText()));
            } catch (NullPointerException e) {
                logger.info("Proxy entry PostData not found");
            }
        }
        if (cookies) {
            try {
                logger.info("Proxy entry Cookies: " + entry.getRequest().getCookies());
            } catch (NullPointerException e) {
                logger.info("Proxy entry Cookies not found");
            }
        }
    }

    public static void createProxyHar(BrowserMobProxy proxy) {
        proxy.newHar();
    }
}
