package com.nicole.urlshortener.service;

public class UrlValidator {

    private static final String LONG_URL_PATTERN =
            "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
    private static final String SHORT_URL_PATTERN = "http:\\/\\/nicole.z\\/[a-zA-Z0-9]{2}";

    public static boolean validateLongUrl(String longUrl) {
        return isUrlAndPatternMatch(longUrl, LONG_URL_PATTERN);
    }

    public static boolean validateShortUrl(String shortUrl) {
        return isUrlAndPatternMatch(shortUrl, SHORT_URL_PATTERN);
    }

    private static boolean isUrlAndPatternMatch(String url, String pattern) {
        return url.matches(pattern);
    }

    private UrlValidator() {}
}
