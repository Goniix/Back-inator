package utils;

import jakarta.servlet.http.HttpServletRequest;

public class WebHelper {
    public static Integer getIntParam(String param, Integer min, Integer max, Integer def) {
        int res;
        try {
            if (min == null)
                min = Integer.MIN_VALUE;
            if (max == null)
                max = Integer.MAX_VALUE;

            res = Integer.parseInt(param);
            if (res < min || res > max)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            res = def;
        }
        return res;
    }

    // public static String getStringParam(String param, String defaultVal){
    // if (param == null || param.isEmpty()) return defaultVal;
    // return param;
    // }
    public static String getStringParam(HttpServletRequest req, String param, String defaultVal) {
        String value = req.getParameter(param);
        if (value == null || value.isEmpty())
            return defaultVal;
        return value;
    }
}
