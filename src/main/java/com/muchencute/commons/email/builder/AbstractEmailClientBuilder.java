package com.muchencute.commons.email.builder;

import com.muchencute.commons.email.client.AbstractEmailClient;

public abstract class AbstractEmailClientBuilder {

    private String mHostName;

    private int mSmtpPort;

    private String mUsername;

    private String mPassword;

    private boolean mSSL = true;

    private String mFrom;

    AbstractEmailClientBuilder(String hostName, int smtpPort, String username, String password) {

        mHostName = hostName;
        mSmtpPort = smtpPort;
        mUsername = username;
        mPassword = password;
        mFrom = username;
    }

    public AbstractEmailClientBuilder ssl(boolean ssl) {
        mSSL = ssl;
        return this;
    }

    public abstract AbstractEmailClient build();

    public String getFrom() {

        return mFrom;
    }

    public AbstractEmailClientBuilder setFrom(String from) {

        mFrom = from;
        return this;
    }

    public String getHostName() {

        return mHostName;
    }

    public AbstractEmailClientBuilder setHostName(String hostName) {

        mHostName = hostName;
        return this;
    }

    public int getSmtpPort() {

        return mSmtpPort;
    }

    public AbstractEmailClientBuilder setSmtpPort(int smtpPort) {

        mSmtpPort = smtpPort;
        return this;
    }

    public String getUsername() {

        return mUsername;
    }

    public AbstractEmailClientBuilder setUsername(String username) {

        mUsername = username;
        return this;
    }

    public String getPassword() {

        return mPassword;
    }

    public AbstractEmailClientBuilder setPassword(String password) {

        mPassword = password;
        return this;
    }

    public boolean isSSL() {

        return mSSL;
    }

    public AbstractEmailClientBuilder setSSL(boolean SSL) {

        mSSL = SSL;
        return this;
    }
}
