package com.omarazzam.paymentguard.evaluation.entity.connector;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectorFactory {

    @Autowired
    AndConnector andConnector;
    @Autowired
    OrConnector orConnector;

   public  Connector createConnector(String connector) {

        switch (connector){
            case "&" : return  andConnector;
            case "|" : return  orConnector;
            default : throw  new UnsupportedOperationException("Invalid Operatot");
        }

    }
}
