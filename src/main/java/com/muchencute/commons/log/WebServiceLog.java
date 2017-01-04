package com.muchencute.commons.log;

import com.muchencute.commons.date.DateFormatter;
import org.apache.log4j.Logger;

import java.io.PrintStream;

@SuppressWarnings("ALL")
public class WebServiceLog {

    private String mProjectName;
    private String mClassName;
    private String mFunctionNo;
    private int mErrorNo;
    private String mErrorInfo;
    private String mParameters;

    public WebServiceLog(String projectName, String className, String functionNo, String parameters) {
        mParameters = parameters;
        mProjectName = projectName;
        mClassName = className;
        mFunctionNo = functionNo;
    }

    public WebServiceLog setError(int errorNo, String errorInfo) {
        mErrorNo = errorNo;
        mErrorInfo = errorInfo;
        return this;
    }

    public WebServiceLog setSuccess() {
        mErrorNo = 0;
        mErrorInfo = "";
        return this;
    }

    public void println(PrintStream printStream) {
        String log = String.format("%s,%s,%s,%s,%s,[ErrorNo=%d],[ErrorInfo=%s]", mProjectName, mClassName, mFunctionNo,
                DateFormatter.getCustomizedDateTimeFormat("yyyyMMddHHmmssSSS"), mParameters, mErrorNo, mErrorInfo);
        printStream.println(log);
    }

    public void println(Class clazz) {
        String log = String.format("%s,%s,%s,%s,%s,[ErrorNo=%d],[ErrorInfo=%s]", mProjectName, mClassName, mFunctionNo,
                DateFormatter.getCustomizedDateTimeFormat("yyyyMMddHHmmssSSS"), mParameters, mErrorNo, mErrorInfo);
        Logger.getLogger(clazz).info(log);
    }
}
