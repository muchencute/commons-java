package com.muchencute.commons.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public interface Executed {

    /**
     * @param resultSet 结果集，若无返回 null
     * @param outParams 出参数组，若无返回 null，对于 PostgreSQL 此处始终为 null
     * @throws SQLException
     */
    void executed(ResultSet resultSet, ArrayList<Object> outParams) throws SQLException;
}
