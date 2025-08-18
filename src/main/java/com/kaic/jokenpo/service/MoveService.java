package com.kaic.jokenpo.service;

import org.springframework.stereotype.Service;

import com.kaic.jokenpo.model.Move;

@Service
public class MoveService {

    int playerScore = 0;
    int machineScore = 0;

    // This service handles the player's move logic

    public String playerMove(String player, Move move) {

        System.out.println("Player move: " + player);
        System.out.println("Machine move: " + move);
        System.out.println("Player score: " + playerScore);

        if (player == move.toString()) {
            return "Draw, try again";
        }
        if (player.equals("ROCK") && move == Move.SCISSORS) {
            playerScore++;
            return "You win! Rock crushes scissors";
        }
        if (player.equals("ROCK") && move == Move.PAPER) {
            machineScore++;
            return "You lose! Paper covers rock";
        }
        if (player.equals("PAPER") && move == Move.ROCK) {
            playerScore++;
            return "You win! Paper covers rock";
        }
        if (player.equals("PAPER") && move == Move.SCISSORS) {
            machineScore++;
            return "You lose! Scissors cut paper";
        }
        if (player.equals("SCISSORS") && move == Move.PAPER) {
            playerScore++;
            return "You win! Scissors cut paper";
        }
        if (player.equals("SCISSORS") && move == Move.ROCK) {
            machineScore++;
            return "You lose! Rock crushes scissors";

        }

        return null;

    }
}
