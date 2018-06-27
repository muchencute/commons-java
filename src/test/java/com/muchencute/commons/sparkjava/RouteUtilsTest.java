package com.muchencute.commons.sparkjava;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.HashSet;

public class RouteUtilsTest extends TestCase {

    public void testMatchParameterizedRoute() {

        String route = "/api/v1/permissionuser/:id/role/:rid";
        String path = "/api/v1/permissionuser/admin/role/管理员";
        Assert.assertTrue(RouteUtils.matchParameterizedRoute(route, path));
        path = "/api/v1/permissionuser/admin/role";
        Assert.assertFalse(RouteUtils.matchParameterizedRoute(route, path));

        route = "/api/v1/permissionuser";
        path = "/api/v1/permissionuser";
        Assert.assertTrue(RouteUtils.matchParameterizedRoute(route, path));

        route = "patch./api/v1/permissionrole/:roleName?status";
        path = "patch./api/v1/permissionrole/管理员?status=removed";
        Assert.assertTrue(RouteUtils.matchParameterizedRoute(route, path));
    }

    public void testMatchQueryParameters() {

        String route = "patch./api/v1/permissionrole/:roleName?status";
        Assert.assertTrue(RouteUtils.matchQueryParameters(route, new HashSet<String>() {{
            add("roleName");
            add("status");
        }}));

        route = "patch./api/v1/permissionrole?roleName&status";
        Assert.assertTrue(RouteUtils.matchQueryParameters(route, new HashSet<String>() {{
            add("roleName");
            add("status");
        }}));
        Assert.assertFalse(RouteUtils.matchQueryParameters(route, new HashSet<String>() {{
            add("roleName");
        }}));
    }
}