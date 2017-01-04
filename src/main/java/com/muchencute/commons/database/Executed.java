package com.muchencute.commons.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public interface Executed {

    void executed(ResultSet resultSet, ArrayList<Object> outParams) throws SQLException;
}
