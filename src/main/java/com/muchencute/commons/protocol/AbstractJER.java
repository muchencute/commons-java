package com.muchencute.commons.protocol;

import org.json.JSONObject;

@SuppressWarnings("ALL")
abstract class AbstractJER<T> {

    T mResult;
    private JSONObject mReturn = new JSONObject();
    private JSONObject mError = new JSONObject();

    AbstractJER() {
        initResult();
        mReturn.put("error", mError);
        mReturn.put("result", mResult);
    }

    abstract void initResult();

    public void setError(int no, String info) {
        mError.put("no", no);
        mError.put("info", info);
    }

    public void setSuccess() {
        mError.put("no", 0);
        mError.put("info", "");
    }

    public T getResult() {
        return mResult;
    }

    @Override
    public String toString() {
        return mReturn.toString();
    }

    public int getErrorNo() {
        return mError.optInt("no", 0);
    }

    public String getErrorInfo() {
        return mError.optString("info", "");
    }

}
