package com.muchencute.commons.sparkjava;

import java.util.StringTokenizer;

public class RouteUtils {

    /**
     * 对于 URL 中包含 : 参数的路由与真实 Path 之间进行匹配
     * @param route 原始路由
     * @param path 真实请求 Path 部分
     * @return 匹配返回 true, 不匹配返回 false
     */
    public static boolean matchParameterizedRoute(String route, String path) {

        StringTokenizer routeTokenizer = new StringTokenizer(route, "/");
        StringTokenizer pathTokenizer = new StringTokenizer(path, "/");

        if (routeTokenizer.countTokens() != pathTokenizer.countTokens()) {
            return false;
        }

        while (routeTokenizer.hasMoreTokens() && routeTokenizer.hasMoreTokens()) {
            String routeToken = routeTokenizer.nextToken();
            String pathToken = pathTokenizer.nextToken();
            if (!routeToken.startsWith(":") && !routeToken.equals(pathToken)) {
                return false;
            }
        }

        return true;
    }

}
