package com.omarazzam.paymentguard.evaluation.service.testsenario.connector;

public class NotConnector implements Connector{



    @Override
    public boolean evaluate(boolean cond1, boolean cond2) {
        return false;
    }
}
