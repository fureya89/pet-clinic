package com.example.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/vets")
@Controller
public class VetController {

    @RequestMapping({"","/", "vets/index", "vets/index.html"})
    public String listVets(){
        return "vets/index";
    }
}
