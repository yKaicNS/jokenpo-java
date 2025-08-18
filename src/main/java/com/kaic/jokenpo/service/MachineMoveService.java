package com.kaic.jokenpo.service;

import org.springframework.stereotype.Service;

import com.kaic.jokenpo.model.Move;

@Service
public class MachineMoveService {
   // Generates a random move for the machine
    public Move getMachineMove(){
        Move [] move= Move.values();
        int randomIndex = (int) (Math.random() * move.length);
        return move[randomIndex];
    }

}
