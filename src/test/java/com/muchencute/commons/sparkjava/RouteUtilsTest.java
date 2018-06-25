package com.muchencute.commons.sparkjava;

import junit.framework.Assert;
import junit.framework.TestCase;

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

    }
}