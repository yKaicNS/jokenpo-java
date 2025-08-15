package com.kaic.jokenpo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homeController() {
        return "TelaInicial/index";
    }

  

   
  
   
    

}
