package com.xml.xmlparser.controller;

import com.xml.xmlparser.service.CastObceService;
import com.xml.xmlparser.service.ObecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {


    @Autowired
    private ObecService obecService;

    @Autowired
    private CastObceService castObceService;


    @EventListener(ApplicationReadyEvent.class)
    public void add(){
        obecService.saveObec();
        castObceService.saveObec();

    }


}
