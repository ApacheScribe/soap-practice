package com.example.soap.config;

import com.example.soap.client.BankClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("com.example.soap.wsdl");
        return jaxb2Marshaller;
    }

    @Bean
    public BankClient clientConnector(Jaxb2Marshaller jaxb2Marshaller) {
        BankClient client = new BankClient();
        client.setDefaultUri("http://www.thomas-bayer.com/axis2/services/BLZService");
        client.setMarshaller(jaxb2Marshaller);
        client.setUnmarshaller(jaxb2Marshaller);
        return client;
    }
}
