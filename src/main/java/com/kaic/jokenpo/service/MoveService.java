package com.kaic.jokenpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kaic.jokenpo.model.Move;

@Service
public class MoveService {

    private int playerScore = 0;
    private int machineScore = 0;

    @Autowired
    private MachineMoveService machineMoveService;

    public int getPlayerScore() {
        return playerScore;
    }

    public int getMachineScore() {
        return machineScore;
    }

    private void addPlayerScore() {
        playerScore++;
    }

    private void addMachineScore() {
        machineScore++;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void setMachineScore(int machineScore) {
        this.machineScore = machineScore;
    }

    public String play(String playerMove) {
        Move machineMove = machineMoveService.getMachineMove();
        return playerMove(playerMove, machineMove);
    }

    private String playerMove(String player, Move move) {
        if (player.equals(move.toString())) {
            return "Draw! Player: " + playerScore + " | Machine: " + machineScore;
        }

        switch (player) {
            case "ROCK":
                if (move == Move.SCISSORS) {
                    addPlayerScore();
                    return "You win! Rock crushes scissors. Player: " + playerScore + " | Machine: " + machineScore;
                } else {
                    addMachineScore();
                    return "You lose! Paper covers rock. Player: " + playerScore + " | Machine: " + machineScore;
                }

            case "PAPER":
                if (move == Move.ROCK) {
                    addPlayerScore();
                    return "You win! Paper covers rock. Player: " + playerScore + " | Machine: " + machineScore;
                } else {
                    addMachineScore();
                    return "You lose! Scissors cut paper. Player: " + playerScore + " | Machine: " + machineScore;
                }

            case "SCISSORS":
                if (move == Move.PAPER) {
                    addPlayerScore();
                    return "You win! Scissors cut paper. Player: " + playerScore + " | Machine: " + machineScore;
                } else {
                    addMachineScore();
                    return "You lose! Rock crushes scissors. Player: " + playerScore + " | Machine: " + machineScore;
                }

            default:
                return "Invalid move!";
        }

    }

    public String winner() {
        if (getPlayerScore() == 3 || getMachineScore() == 3) {
            if (getPlayerScore() > getMachineScore()) {
                setPlayerScore(0);
                setMachineScore(0);
                return "Player is the winner!";
            } else {
                setMachineScore(0);
                setPlayerScore(0);
                return "Machine is the winner!";
            }
        }

        return "error";

    }
}
