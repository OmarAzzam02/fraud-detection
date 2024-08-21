package com.omarazzam.paymentguard.evaluation.service.testsenario.connector;


import org.springframework.stereotype.Component;

@Component
public class ConnectorFactory {

   public  Connector createConnector(String connector) {

        switch (connector){
            case "&" : return new AndConnector();
            case "|" : return  new  OrConnector();
            case "!" : return new NotConnector();
            default : return null;
        }

    }
}
