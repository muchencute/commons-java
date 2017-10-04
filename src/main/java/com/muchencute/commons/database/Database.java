package com.muchencute.commons.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Database {

    private static Database instance;

    private DataSource dataSource;

    private Database(String contextName) {

        try {
            Context initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup(contextName);
        } catch (NamingException e) {
            e.printStackTrace();
            // System.exit(0);
        }
    }

    public static DataSource getDataSource() {

        return instance.dataSource;
    }

    public static void initContext(String contextName) {

        instance = new Database(contextName);
    }
}
