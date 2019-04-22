package com.sabaos.core;

import android.content.Context;

import java.lang.reflect.Method;

public class SabaUtils {

    public static final String SECURITY_LEVEL_GENERIC = "Generic";
    public static final String SECURITY_LEVEL_ORG = "HSL";
    public static final String SECURITY_LEVEL_SECURE = "VHSL";

    public static boolean isSabaBuild() {
        return true;
    }

    /**
     * Read a system defined property and return its value
     *
     * @param context Application context
     * @param key     System property to read
     * @return The value of system property specified by key parameter or null
     */
    private static String getSystemProperty(Context context, String key) {
        // Using reflection to access hidden SystemProperties class in android API
        //   based on https://stackoverflow.com/q/2641111
        try {
            ClassLoader cl = context.getClassLoader();
            Class SystemProperties = cl.loadClass("android.os.SystemProperties");
            Class[] paramTypes = new Class[1];
            paramTypes[0] = String.class;
            Method get = SystemProperties.getMethod("get", paramTypes);
            Object[] params = {key};
            return (String) get.invoke(SystemProperties, params);
        } catch (Exception e) {
            return null;
        }
    }

    /** Return the saba security level defined in Android OS build
     *
     * @param context Application context
     * @return Security level of OS
     */
    public static String getSecurityLevel(Context context) {
        String level = getSystemProperty(context, "ro.build.seclevel");
        if (level == null || level.isEmpty()) return SECURITY_LEVEL_GENERIC;
        return level;
    }
}
