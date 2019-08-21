package com.sabaos.core;

import android.content.Context;

import java.lang.reflect.Method;

public class SabaUtils {
    private static final String LEVEL_KEY = "ro.build.seclevel";

    public static final int SECURITY_LEVEL_TESTING = 0;
    public static final int SECURITY_LEVEL_PUBLIC = 1;
    public static final int SECURITY_LEVEL_BASIC = 2;
    public static final int SECURITY_LEVEL_VIP = 3;
    public static final int SECURITY_LEVEL_SECURE = 4;

    /**
     * Determine whether the OS is SabaOS based
     *
     * @param context Application context
     * @return True if we are running on SabaOS
     */
    public static boolean isSabaBuild(Context context) {
        String level = getSystemProperty(context, LEVEL_KEY);
        return level != null && !level.isEmpty();
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

    /**
     * Return the saba security level defined in Android OS build
     *
     * @param context Application context
     * @return Saba Security level of the OS, an integer in [0, 4]
     */
    public static int getSecurityLevel(Context context) {
        String level = getSystemProperty(context, LEVEL_KEY);
        if (level == null)
            level = "";
        level = level.toUpperCase();
        if (level.equals("TEST"))
            return SECURITY_LEVEL_TESTING;
        if (level.isEmpty() || level.equals("PUBLIC"))
            return SECURITY_LEVEL_PUBLIC;
        if (level.equals("HSL"))
            return SECURITY_LEVEL_BASIC;
        if (level.equals("VHSL"))
            return SECURITY_LEVEL_VIP;
        if (level.equals("UHSL"))
            return SECURITY_LEVEL_SECURE;
        return SECURITY_LEVEL_BASIC;
    }
}
