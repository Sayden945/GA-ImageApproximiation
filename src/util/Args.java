package util;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for parsing command line arguments.
 */
public final class Args {
    private Args() {
    }

    //
    public static Map<String, String> parse(String[] argv) {
        Map<String, String> options = new HashMap<>();
        for (int i = 0; i < argv.length; i++) {
            String a = argv[i];
            if (!a.startsWith("--")) {
                throw new IllegalArgumentException("Invalid argument: " + a + "Expected --key value");
            }

            String key, value;
            key = a.substring(2);
            if (i + 1 < argv.length && !argv[i + 1].startsWith("--")) {
                value = argv[++i];
            } else {
                value = argv[i];
            }
            if (key.isEmpty())
                throw new IllegalArgumentException("Missing key for argument: " + a);
            options.put(key, value);
        }

        return options;
    }


    /**
     * Utility methods for retrieving typed values from a map of options.
     */
    public static String str(Map<String, String> o, String key, String def) {
        String v = o.get(key);
        return v == null ? def : v;
    }

    public static int i32(Map<String, String> o, String key, int def) {
        String v = o.get(key);
        return (v == null) ? def : Integer.parseInt(v);
    }

    public static double f64(Map<String, String> o, String key, double def) {
        String v = o.get(key);
        return v == null ? def : Double.parseDouble(v);
    }

    public static boolean flag(Map<String, String> o, String key, boolean def) {
        String v = o.get(key);
        if (v == null) return def;
        if (v.isEmpty()) return true;
        return Boolean.parseBoolean(v.trim());

    }
}