package com.muchencute.commons.database;

import java.util.List;

/**
 * @author miguoliang
 */
public final class GenericList<T> {

    private final List<T> list;

    private final String sqlType;

    public GenericList(final List<T> list, final String sqlType) {

        this.list = list;
        this.sqlType = sqlType;
    }

    List<T> getList() {

        return list;
    }

    String getSqlType() {

        return sqlType;
    }
}
