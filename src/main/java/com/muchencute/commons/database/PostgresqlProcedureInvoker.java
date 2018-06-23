package com.muchencute.commons.database;

import javax.sql.DataSource;

public final class PostgresqlProcedureInvoker extends ProcedureInvoker {

    public PostgresqlProcedureInvoker(DataSource dataSource) {

        super(dataSource);
    }

    @Override
    protected String getFormatter() {

        return "SELECT %s(%s)";
    }
}
