 /* 
 * @author  Kaic (@yKaicNS)
 * @version 1.0
 * @since   2025-08-20 
 */

package com.kaic.jokenpo.service;

import org.springframework.stereotype.Service;

import com.kaic.jokenpo.model.Move;

@Service
public class MachineMoveService {
    public Move getMachineMove(){
        Move [] move= Move.values();
        int randomIndex = (int) (Math.random() * move.length);
        return move[randomIndex];
    }

}
