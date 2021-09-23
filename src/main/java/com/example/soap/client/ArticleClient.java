package com.example.soap.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.example.soap.wsdl.GetArticleRequest;
import com.example.soap.wsdl.GetArticleResponse;

public class ArticleClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(ArticleClient.class);

    public GetArticleResponse getArticle(int id) {
        GetArticleRequest getArticleRequest = new GetArticleRequest();
        getArticleRequest.setId(id);
        return (GetArticleResponse) getWebServiceTemplate().marshalSendAndReceive(getArticleRequest);
    }
}
