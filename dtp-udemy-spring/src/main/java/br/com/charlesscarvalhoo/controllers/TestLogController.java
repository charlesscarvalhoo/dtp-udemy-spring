package br.com.charlesscarvalhoo.controllers;

import br.com.charlesscarvalhoo.services.PersonServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/test/v1")
public class TestLogController {

    private final Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    @GetMapping("/test")
    public String TestLog(){


        logger.info("This is an INFO log");
        logger.debug("This is an DEBUG log");
        logger.warn("This is an WARN log");
        logger.error("This is an ERROR log");


        return "Logs generated successfully";
    }
}
