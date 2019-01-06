package com.example.dylan.demoproject.Utils;

import android.net.Uri;
import android.telephony.PhoneNumberUtils;

public class UriUtils {

    public static String getWebsiteUrl(String uriString) {

        Uri uri = Uri.parse(uriString);
        if (uri.getScheme() == null) {
            uri = uri.buildUpon().scheme("http").build();
        }
        return uri.toString();
    }

    public static String getTelephoneUri(String uriString) {

        /**
         * normalizeNumber to handle extension code. Many phone numbers in the sample API end in x12345 to represent extension 12345.
         * normalize replaces the x with the extension number code (typically 9 in North America, 0 in Europe)
         * Source: {@link <a href="https://en.wikipedia.org/wiki/Extension_%28telephone%29"/a>}
         */
        String normalizeNumber = PhoneNumberUtils.normalizeNumber(uriString);
        Uri uri = Uri.parse(normalizeNumber);
        if (uri.getScheme() == null) {
            uri = uri.buildUpon().scheme("tel").build();
        }
        return uri.toString();
    }

    public static String getMailUri(String uriString) {

        Uri uri = Uri.parse(uriString);
        if (uri.getScheme() == null) {
            uri = uri.buildUpon().scheme("mailto").build();
        }
        // Have to explicitly remove "/" from "mailto:/" because both GMail and Samsung Email open with recipient: "/x@y.z".
        // The prepending slash is not part of the intended Email address.
        return uri.toString().replace("/", "");
    }
}
