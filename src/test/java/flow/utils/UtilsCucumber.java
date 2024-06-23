package flow.utils;

import java.util.Collection;
import java.util.regex.Pattern;


public class UtilsCucumber {

    public static String getFeature(String uri) {
        var pattern = Pattern.compile(".*features/(.*?)\\.feature?");
        var matcher = pattern.matcher(uri);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public static String getTuf(Collection<String> tags) {
        var tuf = getTag("@tmsLink", tags);
        if (!tuf.isEmpty()) {
            return tuf.split("=")[1];
        }
        return "";
    }

    public static String getTag(String tag, Collection<String> tags) {
        var search_tag = tags.stream().filter(t -> t.contains(tag)).findFirst();
        return search_tag.orElse("").replace("@", "");
    }
}