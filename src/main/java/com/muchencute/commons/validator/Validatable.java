package com.muchencute.commons.validator;

@SuppressWarnings("ALL")
public interface Validatable {

    /**
     * 用户自定义合法性检验接口
     *
     * @param value 值对象
     * @return 通过返回 true，不通过返回 false
     */
    boolean isTrue(Object value);

}
