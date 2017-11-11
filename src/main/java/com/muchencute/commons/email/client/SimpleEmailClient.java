package com.muchencute.commons.email.client;

import com.muchencute.commons.email.builder.AbstractEmailClientBuilder;
import javafx.util.Pair;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SimpleEmailClient extends AbstractEmailClient {

    private SimpleEmail mEmail;

    public SimpleEmailClient(AbstractEmailClientBuilder builder) {

        super(builder);

        mEmail = new SimpleEmail();
        mEmail.setHostName(builder.getHostName());
        mEmail.setSmtpPort(builder.getSmtpPort());
        mEmail.setAuthentication(builder.getUsername(), builder.getPassword());
        mEmail.setSSLOnConnect(builder.isSSL());

        try {
            mEmail.setFrom(builder.getFrom());
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send() {

        try {
            mEmail.setMsg(mMessage);
            mEmail.setSubject(mSubject);
            for (Pair<String, String> to : mTos) {
                mEmail.addTo(to.getKey(), to.getValue());
            }
            mEmail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
