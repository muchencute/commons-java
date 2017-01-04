package com.muchencute.commons.session;

import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.ServiceGroupContext;
import org.apache.axis2.transport.http.HTTPConstants;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("ALL")
public final class Axis2Session extends AbstractSession {

    private ServiceGroupContext mServiceGroupContext;

    public Axis2Session() {
        mServiceGroupContext = MessageContext.getCurrentMessageContext().getServiceGroupContext();
    }

    public HTTPSession getHTTPSession() {
        return new HTTPSession((HttpServletRequest) MessageContext.getCurrentMessageContext().getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST));
    }

    @Override
    void nativePut(String key, Object value) {
        mServiceGroupContext.setProperty(key, value);
    }

    @Override
    Object nativeGet(String key) {
        return mServiceGroupContext.getProperty(key);
    }

    @Override
    void nativeRemove(String key) {
        mServiceGroupContext.removeProperty(key);
    }
}
