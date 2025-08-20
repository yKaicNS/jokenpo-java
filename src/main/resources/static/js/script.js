/**
 * @file Main script for the Jokenpô game interface.
 * @description This script handles all the user interactions, communicates with the backend API via fetch,
 * and updates the DOM to reflect the game state.
 * @author Kaic @yKaicNS
 * @version 1.0
 */

document.addEventListener('DOMContentLoaded', () => {
    const moveIcons = {
        ROCK: '<i class="fa-solid fa-hand-back-fist"></i>',
        PAPER: '<i class="fa-solid fa-hand"></i>',
        SCISSORS: '<i class="fa-solid fa-hand-scissors"></i>'
    };

    const buttons = document.querySelectorAll(".move-btn");
    const playerScoreEl = document.getElementById("playerScore");
    const machineScoreEl = document.getElementById("machineScore");
    const startTextEl = document.getElementById("start-text");
    const resultDisplayEl = document.getElementById("result-display");
    const playerChoiceIconEl = document.getElementById("player-choice-icon");
    const machineChoiceIconEl = document.getElementById("machine-choice-icon");
    const roundWinnerEl = document.getElementById("round-winner-text");

    const modalEl = document.getElementById("game-over-modal");
    const modalTitleEl = document.getElementById("modal-title");
    const modalMessageEl = document.getElementById("modal-message");
    const modalIconEl = document.getElementById("modal-icon");
    const playAgainBtn = document.getElementById("play-again-btn");

    buttons.forEach(button => {
        button.addEventListener("click", handlePlayerMove);
    });

    playAgainBtn.addEventListener("click", resetGame);

    async function handlePlayerMove(event) {
        event.preventDefault();
        const playerMove = event.currentTarget.value;

        try {
            const response = await fetch(`/play?move=${playerMove}`);
            const resultText = await response.text();
            const machineMove = getMachineMoveFromResult(resultText, playerMove);

            updateUI(playerMove, machineMove, resultText);
            updatePlayerScore();
            updateMachineScore();

        } catch (err) {
            console.error("Error processing move:", err);
            roundWinnerEl.textContent = "Error!";
        }
    }

    function updateUI(playerMove, machineMove, resultText) {
        startTextEl.classList.add("hidden");
        resultDisplayEl.classList.remove("hidden");

        playerChoiceIconEl.innerHTML = moveIcons[playerMove];
        machineChoiceIconEl.innerHTML = machineMove ? moveIcons[machineMove] : '<i class="fa-solid fa-question"></i>';

        if (resultText.includes("You win!")) {
            roundWinnerEl.textContent = "YOU WIN!";
            roundWinnerEl.style.color = "#28a745";
        } else if (resultText.includes("You lose!")) {
            roundWinnerEl.textContent = "YOU LOSE!";
            roundWinnerEl.style.color = "#dc3545";
        } else if (resultText.includes("Draw!")) {
            roundWinnerEl.textContent = "DRAW!";
            roundWinnerEl.style.color = "#6c757d";
        }
    }

    function getMachineMoveFromResult(resultText, playerMove) {
        if (resultText.includes("Draw!")) return playerMove;
        if (playerMove === "ROCK") return resultText.includes("You win!") ? "SCISSORS" : "PAPER";
        if (playerMove === "PAPER") return resultText.includes("You win!") ? "ROCK" : "SCISSORS";
        if (playerMove === "SCISSORS") return resultText.includes("You win!") ? "PAPER" : "ROCK";
        return null;
    }

    function updatePlayerScore() {
        fetch("/scorePlayer").then(res => res.json()).then(data => {
            playerScoreEl.innerText = data;
            checkGameWinner();
        });
    }

    function updateMachineScore() {
        fetch("/scoreMachine").then(res => res.json()).then(data => {
            machineScoreEl.innerText = data;
            checkGameWinner();
        });
    }

    function checkGameWinner() {
        fetch("/Winner").then(res => res.text()).then(data => {
            if (data !== "error") {
                setTimeout(() => {
                    showGameOverModal(data);
                }, 500);
            }
        });
    }


    function showGameOverModal(winnerMessage) {
        if (winnerMessage.includes("Player")) {
            modalTitleEl.textContent = "You Win!";
            modalMessageEl.textContent = "Congratulations! You are the Jokenpô champion!";
            modalIconEl.className = "fa-solid fa-trophy";
            modalIconEl.style.color = "#f5a623";
        } else {
            modalTitleEl.textContent = "You Lose!";
            modalMessageEl.textContent = "Not this time, but don't give up. Try again!";
            modalIconEl.className = "fa-solid fa-robot";
            modalIconEl.style.color = "#dc3545";
        }
        modalEl.classList.remove("hidden");
        modalEl.classList.add("show");
    }

    function resetGame() {
        modalEl.classList.remove("show");
        modalEl.classList.add("hidden");
        playerScoreEl.textContent = '0';
        machineScoreEl.textContent = '0';
        resultDisplayEl.classList.add("hidden");
        startTextEl.classList.remove("hidden");
    }

    updatePlayerScore();
    updateMachineScore();
});