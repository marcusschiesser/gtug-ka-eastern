package org.gtug.karlsruhe.bunnycacher.server.util;

public final class GaeUtils {

    private GaeUtils() {
        throw new IllegalAccessError();
    }

    public static boolean isDevelopmentMode() {
        if (System.getProperty("com.google.appengine.runtime.environment").contains("Development")) {
            return true;
        }
        return false;
    }

}
