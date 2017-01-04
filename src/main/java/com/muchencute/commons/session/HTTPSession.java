package com.muchencute.commons.session;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("ALL")
public class HTTPSession extends AbstractSession {

    private HttpServletRequest mHttpServletRequest;

    public HTTPSession(HttpServletRequest httpServletRequest) {
        mHttpServletRequest = httpServletRequest;
    }

    @Override
    void nativePut(String key, Object value) {
        mHttpServletRequest.setAttribute(key, value);
    }

    @Override
    Object nativeGet(String key) {
        return mHttpServletRequest.getAttribute(key);
    }

    void nativeRemove(String key) {
        mHttpServletRequest.removeAttribute(key);
    }

    public HttpServletRequest getHttpServletRequest() {
        return mHttpServletRequest;
    }
}
