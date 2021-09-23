package com.example.soap.res;

import com.example.soap.client.ArticleClient;
import com.example.soap.config.SoapClientConfig;
import com.example.soap.wsdl.Article;
import com.example.soap.wsdl.GetArticleResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Default {

    private static final Logger log = LoggerFactory.getLogger(Default.class);

    @GetMapping("/")
    String index() {
        return "index";
    }

    @GetMapping("/article/{id}")
    ResponseEntity<String> getArticle(@PathVariable int id) {
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        try {
            log.info("GET Article with id: " + id);

            // runner
            AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
                    SoapClientConfig.class);
            ArticleClient client = annotationConfigApplicationContext.getBean(ArticleClient.class);
            annotationConfigApplicationContext.close();

            GetArticleResponse res = client.getArticle(id);
            String article = res.getArticle().getName();
            if (article != null) {
                log.info("Found Article with id: " + id);
            } else {
                log.info("Cound not find Article with id: " + id);
            }
            response = ResponseEntity.status(HttpStatus.OK).body(article);
        } catch (Exception e) {
            log.info("Error - GET Article with id: " + id);
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return response;
    }
}
