package com.muchencute.commons.sparkjava;

import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.StringTokenizer;

public class RouteUtils {

    /**
     * 对于 URL 中包含 : 参数的路由与真实 Path 之间进行匹配
     *
     * @param route 原始路由
     * @param path  真实请求 Path 部分
     * @return 匹配返回 true, 不匹配返回 false
     */
    public static boolean matchParameterizedRoute(String route, String path) {

        StringTokenizer routeTokenizer = new StringTokenizer(StringUtils.substringBefore(route, "?"), "/");
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

    /**
     * 判断实际请求的查询参数是否满足路由的定义
     *
     * @param route              原始路由，必须包含?，否则视为没有查询参数
     * @param requestQueryParams 实际请求的查询参数
     * @return 满足则返回 true，不满足则返回 false
     */
    public static boolean matchQueryParameters(String route, Set<String> requestQueryParams) {

        final String rawRouteQueryParameters = StringUtils.substringAfter(route, "?");
        StringTokenizer routeTokenizer = new StringTokenizer(rawRouteQueryParameters, "&");

        while (routeTokenizer.hasMoreTokens()) {
            String routeToken = routeTokenizer.nextToken();
            if (!requestQueryParams.contains(routeToken)) {
                return false;
            }
        }

        return true;
    }

}
