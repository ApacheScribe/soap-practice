package com.example.soap.client;

import javax.xml.bind.JAXBElement;

import com.example.soap.wsdl.GetBankResponseType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class BankClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(BankClient.class);

    public GetBankResponseType getBank(String url, Object request) {
        JAXBElement res = (JAXBElement) getWebServiceTemplate().marshalSendAndReceive(url, request);
        return (GetBankResponseType) res.getValue();
    }
}
