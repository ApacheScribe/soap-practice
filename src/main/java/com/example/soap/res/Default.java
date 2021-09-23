package com.example.soap.res;

import com.example.soap.client.BankClient;
import com.example.soap.wsdl.DetailsType;
import com.example.soap.wsdl.GetBankResponseType;
import com.example.soap.wsdl.GetBankType;
import com.example.soap.wsdl.ObjectFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Default {

    private static final Logger log = LoggerFactory.getLogger(Default.class);

    @Autowired
    BankClient client;

    @GetMapping("/")
    String index() {
        return "index";
    }

    @GetMapping("/bank")
    public DetailsType sum(@RequestParam String code) {
        ObjectFactory objectFactory = new ObjectFactory();
        GetBankType type = new GetBankType();
        type.setBlz(code);
        // BlzServiceAdapter blzServiceAdapter=new BlzServiceAdapter();
        GetBankResponseType response = client.getBank("http://www.thomas-bayer.com/axis2/services/BLZService",
                objectFactory.createGetBank(type));
        return response.getDetails();
    }

}
