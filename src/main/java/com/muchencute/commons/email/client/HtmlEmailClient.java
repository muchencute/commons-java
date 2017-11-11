package com.muchencute.commons.email.client;

import com.muchencute.commons.email.builder.AbstractEmailClientBuilder;
import javafx.util.Pair;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class HtmlEmailClient extends AbstractEmailClient {

    private HtmlEmail mEmail;

    private String mHtmlMessage;

    public HtmlEmailClient(AbstractEmailClientBuilder builder) {

        super(builder);

        mEmail = new HtmlEmail();
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

    public void setHtmlMessage(String htmlMessage) {

        mHtmlMessage = htmlMessage;
    }

    @Override
    public void send() {

        try {
            mEmail.setHtmlMsg(mHtmlMessage);
            mEmail.setTextMsg(mMessage);
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
