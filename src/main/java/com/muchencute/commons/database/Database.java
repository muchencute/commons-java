package com.muchencute.commons.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Database {

    private static Database instance;

    private DataSource dataSource;

    private String mContextName;

    private Database() {

        try {
            Context initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup(mContextName);
        } catch (NamingException e) {
            e.printStackTrace();
            // System.exit(0);
        }
    }

    public static DataSource getDataSource() {

        return instance.dataSource;
    }

    public void initContext(String contextName) {

        this.mContextName = contextName;

        instance = new Database();
    }
}
