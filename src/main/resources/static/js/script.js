function updatePlayerScore() {
    fetch("/scorePlayer")
        .then(response => response.json())
        .then(data => {
            document.getElementById("playerScore").innerText = data;
            updateWinner();
        })

}

function updateMachineScore() {
    fetch("/scoreMachine")
        .then(response => response.json())
        .then(data => {
            document.getElementById("machineScore").innerText = data;
            updateWinner();
        })

}

function updateWinner(){
    fetch("/Winner")
        .then(response => response.text())
        .then(data => {
            if(data != "error"){
            document.getElementById("result").innerText = data;
            alert(data);
            }
        })
 
}


document.addEventListener('DOMContentLoaded', updatePlayerScore);
document.addEventListener('DOMContentLoaded', updateMachineScore);
document.addEventListener('DOMContentLoaded', updateWinner);
