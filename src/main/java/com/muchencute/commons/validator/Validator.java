package com.muchencute.commons.validator;

import com.muchencute.commons.regexp.RegexpUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@SuppressWarnings("ALL")
public class Validator {

    // 手机号码
    private static Pattern cellphonePattern = Pattern.compile("^(13[0-9]|14[0-9]|15[0-9]|18[0-9]|17[0-9])\\d{8}$");
    // Email
    private static Pattern emailPattern = Pattern.compile(RegexpUtils.EMAIL);
    // 用户名
    private static Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9@.-_]+$");
    // 密码
    private static Pattern passwordPattern = Pattern.compile("^[^\\s]+$");
    // 登录验证码
    private static Pattern loginAuthCodePattern = Pattern.compile("^[a-zA-Z0-9]{4}$");

    private boolean mPassed = true;

    private String mErrorMessage = "";

    /**
     * 判断是否为合法的登录验证码
     *
     * @param value        待验证字符串
     * @param errorMessage 错误信息
     * @return 当前规则验证器
     */
    public Validator isLoginAuthCode(String value, String errorMessage) {

        if (!mPassed) return this;

        mPassed = value != null && loginAuthCodePattern.matcher(value).find();

        mErrorMessage = mPassed ? "" : errorMessage;

        return this;
    }

    /**
     * 判断是否为合法的用户名
     *
     * @param value        待验证字符串
     * @param errorMessage 错误信息
     * @return 当前规则验证器
     */
    public Validator isUsername(String value, String errorMessage) {

        if (!mPassed) return this;

        mPassed = value != null && usernamePattern.matcher(value).find();

        mErrorMessage = mPassed ? "" : errorMessage;

        return this;
    }

    /**
     * 判断是否为合法的用户名
     *
     * @param value        待验证字符串
     * @param errorMessage 错误信息
     * @return 当前规则验证器
     */
    public Validator isPassword(String value, String errorMessage) {

        if (!mPassed) return this;

        mPassed = value != null && passwordPattern.matcher(value).find();

        mErrorMessage = mPassed ? "" : errorMessage;

        return this;
    }

    /**
     * 判断是否为合法的手机号码
     *
     * @param value        待验证字符串
     * @param errorMessage 错误信息
     * @return 当前规则验证器
     */
    public Validator isCellphone(String value, String errorMessage) {

        if (!mPassed) return this;

        mPassed = value != null && cellphonePattern.matcher(value).find();

        mErrorMessage = mPassed ? "" : errorMessage;

        return this;
    }

    /**
     * 判断是否为合法的日期
     *
     * @param value        待验证字符串，格式为：yyyy-MM-dd
     * @param errorMessage 错误信息
     * @return 当前规则验证器
     */
    public Validator isDate(String value, String errorMessage) {

        if (!mPassed) return this;

        if (value == null) {
            mPassed = false;
        } else {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = simpleDateFormat.parse(value);
                String src = value.replace("-0", "-");
                String dst = simpleDateFormat.format(date).replace("-0", "-");
                mPassed = src.equals(dst);
            } catch (ParseException e) {
                mPassed = false;
            }
        }

        mErrorMessage = mPassed ? "" : errorMessage;

        return this;
    }

    /**
     * 判断是否为合法的Email
     *
     * @param value        待验证字符串
     * @param errorMessage 错误信息
     * @return 当前规则验证器
     */
    public Validator isEmail(String value, String errorMessage) {

        if (!mPassed) return this;

        mPassed = value != null && emailPattern.matcher(value).find();

        mErrorMessage = mPassed ? "" : errorMessage;

        return this;
    }

    /**
     * 判断是否为真
     *
     * @param value        待验证布尔值
     * @param errorMessage 错误信息
     * @return 当前规则验证器
     */
    public Validator isTrue(boolean value, String errorMessage) {

        if (!mPassed) return this;

        mPassed = value;

        mErrorMessage = mPassed ? "" : errorMessage;

        return this;
    }

    /**
     * 判断字符串是否为非空串(Trim之后)且非null
     *
     * @param value        待验证字符串
     * @param errorMessage 错误信息
     * @return 当前规则验证器
     */
    public Validator isNotNullOrEmptyAfterTrim(String value, String errorMessage) {

        if (!mPassed) return this;

        mPassed = !(value == null || value.trim().isEmpty());

        mErrorMessage = mPassed ? "" : errorMessage;

        return this;
    }

    /**
     * 自定义检验函数
     *
     * @param validatable  检验方法
     * @param value        待验证对象
     * @param errorMessage 错误信息
     * @return 当前规则验证器
     */
    public Validator customized(Validatable validatable, Object value, String errorMessage) {

        if (!mPassed) return this;

        mPassed = validatable.isTrue(value);

        mErrorMessage = mPassed ? "" : errorMessage;

        return this;
    }

    /**
     * 返回判断结果
     *
     * @return true 通过，false 未通过
     */
    public boolean isPassed() {
        return mPassed;
    }

    /**
     * 返回错误信息
     *
     * @return 没有错误为空字符串
     */
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
