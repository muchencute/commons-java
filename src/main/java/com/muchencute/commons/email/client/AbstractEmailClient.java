package com.muchencute.commons.email.client;

import com.muchencute.commons.email.builder.AbstractEmailClientBuilder;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;

public abstract class AbstractEmailClient {

    ArrayList<ImmutablePair<String, String>> mTos = new ArrayList<>();

    String mMessage;

    String mSubject;

    AbstractEmailClient(AbstractEmailClientBuilder builder) {

        mMessage = "";
        mSubject = "";
    }

    public void setMessage(String message) {

        mMessage = message;
    }

    public void setSubject(String subject) {

        mSubject = subject;
    }

    public void addTo(String email, String displayName) {

        mTos.add(new ImmutablePair<>(email, displayName));
    }

    public abstract void send();

}
