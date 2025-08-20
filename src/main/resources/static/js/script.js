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

document.querySelectorAll(".move-btn").forEach(button => {
    button.addEventListener("click", (event) => {
        event.preventDefault();
        const move = event.target.value; 

        fetch(`/play?move=${move}`)
            .then(response => response.text())
            .then(data => {
                document.getElementById("resultPLay").innerText = data;
                updatePlayerScore();
                updateMachineScore();
            })
            .catch(err => console.error(err));
    });
});


document.addEventListener('DOMContentLoaded', updatePlayerScore);
document.addEventListener('DOMContentLoaded', updateMachineScore);
document.addEventListener('DOMContentLoaded', updateWinner);
