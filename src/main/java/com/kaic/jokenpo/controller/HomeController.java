 /* 
 * @author  Kaic (@yKaicNS)
 * @version 1.0
 * @since   2025-08-20 
 */
package com.kaic.jokenpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.kaic.jokenpo.service.MachineMoveService;
import com.kaic.jokenpo.service.MoveService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {
    @Autowired
    MachineMoveService machineMoveService;
    @Autowired
    MoveService moveService;

    @GetMapping("/play")
    public String play(@RequestParam String move) {
        return moveService.play(move);
    }

    @GetMapping("/scorePlayer")
    public int getScorePlayer() {
        return moveService.getPlayerScore();
    }

    @GetMapping("/scoreMachine")
    public int getScoreMachine() {
        return moveService.getMachineScore();
    }

    @GetMapping("/Winner")
    public String getMethodName() {
        return moveService.winner();
    }

}
