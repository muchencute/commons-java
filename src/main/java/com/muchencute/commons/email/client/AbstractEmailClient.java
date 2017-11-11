package com.muchencute.commons.email.client;

import com.muchencute.commons.email.builder.AbstractEmailClientBuilder;
import javafx.util.Pair;

import java.util.ArrayList;

public abstract class AbstractEmailClient {

    ArrayList<Pair<String, String>> mTos = new ArrayList<>();

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

        mTos.add(new Pair<>(email, displayName));
    }

    public abstract void send();

}
