package com.kaic.jokenpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kaic.jokenpo.model.Move;
import com.kaic.jokenpo.service.MachineMoveService;
import com.kaic.jokenpo.service.MoveService;


@RestController
public class HomeController {
    @Autowired
    MachineMoveService machineMoveService;
    @Autowired
    MoveService moveService;

    @PostMapping("/play")
    public String move() {
      
        System.out.println("move");
        machineMoveService.getMachineMove();
    
        


        return moveService.playerMove("ROCK", machineMoveService.getMachineMove());        
        

    }

     
    

  

   
  
   
    

}
