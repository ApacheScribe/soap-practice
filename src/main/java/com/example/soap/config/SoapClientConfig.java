package com.example.soap.config;

import com.example.soap.client.ArticleClient;

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
    public ArticleClient articleClient(Jaxb2Marshaller jaxb2Marshaller) {
        ArticleClient aClient = new ArticleClient();
        aClient.setDefaultUri("http://localhost:8080/ws/");
        aClient.setMarshaller(jaxb2Marshaller);
        aClient.setUnmarshaller(jaxb2Marshaller);
        return aClient;
    }
}
