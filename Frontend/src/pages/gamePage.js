let dealerTotal = 0;
let dealerAce = 0;
let hiddenCard;

let playerTotal = 0;
let playerAce = 0;
let purse = 100;
let wager = 0;

let deck;
let canHit = true;
let message = "";

window.onload = function() {
    buildDeck();
    shuffleDeck();
    bet();
}

function buildDeck() {
    const suits = ['hearts', 'diamonds', 'spades', 'clubs'];
    const rank = ['ace', 'king', 'queen', 'jack', '10', '9', '8', '7', '6', '5', '4', '3', '2'];

    deck = [];

    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < suits.length; j++) {
            for (let k = 0; k < rank.length; k++) {
                deck.push(rank[k] + '_of_' + suits[j]);
            }
        }
    }
}

function shuffleDeck() {
    for (let i = 0; i < deck.length; i++) {
        let random = Math.floor(Math.random() * deck.length);
        let temp = deck[i];
        deck[i] = deck[random];
        deck[random] = temp;
    }
}

function gameStart() {
    document.getElementById('hiddenCard').src = 'css/cards/cardBack.png';

    hiddenCard = deck.pop();
    dealerTotal = getCardValue(hiddenCard);
    dealerAce += checkForAce(hiddenCard);


    document.getElementById("deal").style.display = "none";
    document.getElementById("hiddenCard").style.display = "inline";
    document.getElementById("purse").innerText = purse;


    for (let i = 0; i < 2; i++) {
        let cardImage = document.createElement('img');
        let card = deck.pop();
        cardImage.src = 'css/cards/' + card + '.png';
        cardImage.alt = card;
        playerTotal += getCardValue(card);
        playerAce += checkForAce(card);
        document.getElementById('player-hand').appendChild(cardImage);
        document.getElementById('playerTotal').innerText = playerTotal;
        console.log(card);
        console.log(playerTotal);
    }

    for (let i = 0; i < 1; i++) {
        let cardImage = document.createElement('img');
        let card = deck.pop();
        cardImage.src = 'css/cards/' + card + '.png';
        cardImage.alt = card;
        dealerTotal += getCardValue(card);
        dealerAce += checkForAce(card);
        document.getElementById('dealer-hand').appendChild(cardImage);
        console.log(card);
        console.log(dealerTotal);
    }

    document.getElementById("hit").style.display = "inline";
    document.getElementById("stand").style.display = "inline";

    document.getElementById("hit").addEventListener("click", hit);
    document.getElementById("stand").addEventListener("click", stand);

    if (playerTotal === 21) {
        canHit = false;
        message = "Kenzie 21! You win!";
        purse += wager * 2.5;
        document.getElementById("purse").innerText = purse;
        endGame();
    }

    if (playerTotal === 22) {
        playerTotal -= 10;
        playerAce--;
        document.getElementById('playerTotal').innerText = playerTotal;
    }

    if ((playerTotal === 10 || playerTotal === 11) && purse >= wager) {
        message = "You can double down! Click 'Double Down' to double your wager and get one more card.";
        document.getElementById("double").style.display = "inline";
        document.getElementById("double").addEventListener("click", doubleDown);
    }

    if (playerTotal < 21) {
        canHit = true;
        message = "Click 'Hit' to get another card or 'Stand' to keep your current hand.";
    }
    document.getElementById('results').innerText = message;
}
function getCardValue(card) {
    let value = card.split('_')[0];
    if (isNaN(value)) {
        if (value === 'ace') {
            return 11;
        } else {
            return 10;
        }
    }
    return parseInt(value);
}

function checkForAce(card) {
    if (card[0] === 'a') {
        return 1;
    }
    return 0;
}

function hit() {
    if (!canHit) {
        stand();
        return;
    }

    let cardImage = document.createElement('img');
    let card = deck.pop();
    cardImage.src = 'css/cards/' + card + '.png';
    cardImage.alt = card;
    playerTotal += getCardValue(card);
    playerAce += checkForAce(card);
    document.getElementById('player-hand').appendChild(cardImage);


    while (playerTotal > 21 && playerAce > 0) {
        playerTotal -= 10;
        playerAce--;
        playerTotal = reduceAce(playerTotal, playerAce);
    }

    if (playerTotal > 21) {
        canHit = false;
        message = "You busted! You lose! Click 'Play Again' to play again at same wager or 'Bet' to change your wager.";
        endGame();
    }

    if (playerTotal === 21) {
        canHit = false;
        stand();
    }

    console.log(card);
    console.log(playerTotal);

    document.getElementById('playerTotal').innerText = playerTotal;

    document.getElementById('results').innerText = message;

}

function stand() {
    dealerTotal = reduceAce(dealerTotal, dealerAce);

    document.getElementById("hit").style.display = "none";
    document.getElementById("stand").style.display = "none";
    document.getElementById("double").style.display = "none";

    canHit = false;

    document.getElementById('hiddenCard').src = 'css/cards/' + hiddenCard + '.png';

    while (dealerTotal < 17) {
        let cardImage = document.createElement('img');
        let card = deck.pop();
        cardImage.src = 'css/cards/' + card + '.png';
        cardImage.alt = card;
        dealerTotal += getCardValue(card);
        dealerAce += checkForAce(card);
        document.getElementById('dealer-hand').appendChild(cardImage);
    }

    gameResult();
}

function doubleDown() {
    purse -= wager;
    wager *= 2;
    document.getElementById("wager").innerText = wager;
    document.getElementById("double").style.display = "none";
    document.getElementById("purse").innerText = purse;

    hit();
    stand();

    wager /= 2;
}

function reduceAce(total, ace) {
    while (total > 21 && ace > 0) {
        total -= 10;
        ace--;
    }
    return total;
}

function bet() {
    document.getElementById("hiddenCard").style.display = "none";

    document.getElementById("purse").innerText = purse;

    document.getElementById("bet").style.display = "inline";

    document.getElementById("hit").style.display = "none";
    document.getElementById("stand").style.display = "none";
    document.getElementById("double").style.display = "none";
    document.getElementById("deal").style.display = "none";
    document.getElementById("playAgain").style.display = "none";
    document.getElementById("reset").style.display = "none";

    document.getElementById("bet").addEventListener("click", function () {
        wager = document.getElementById("wagerInput").value;
        if (wager > purse) {
            message = "You don't have enough money to make that bet!";
        } else if (wager < 1) {
            message = "You must bet at least $1.";
        } else if (wager <= purse) {
            purse -= wager;
            document.getElementById("purse").innerText = purse;
            document.getElementById("wager").innerText = wager;

            document.getElementById("deal").style.display = "inline";
            message = "Click 'Deal' to start the game.";

            document.getElementById("deal").addEventListener("click", gameStart);
        }
        document.getElementById('results').innerText = message;
    });
}

function gameResult() {
    if (dealerTotal > 21) {
        message = "Dealer busted! You win! Click 'Play Again' to play again at same wager or 'Bet' to change your wager.";
        purse += wager * 2;
        document.getElementById("purse").innerText = purse;
        endGame();
    } else if (dealerTotal > playerTotal) {
        message = "Dealer has a higher hand! You lose! " +
            "Click 'Play Again' to play again at same wager or 'Bet' to change your wager.";
        endGame();
    } else if (dealerTotal < playerTotal) {
        message = "You have a higher hand! You win! " +
            "Click 'Play Again' to play again at same wager or 'Bet' to change your wager.";
        purse += wager * 2;
        document.getElementById("purse").innerText = purse;
        endGame();
    } else {
        message = "You and the dealer have the same hand! " +
            "It's a Push! Click 'Play Again' to play again at same wager or 'Bet' to change your wager.";
        purse += wager / 2;
        document.getElementById("purse").innerText = purse;
        endGame();
    }

    document.getElementById('dealerTotal').innerText = dealerTotal;
    document.getElementById('playerTotal').innerText = playerTotal;
    document.getElementById('results').innerText = message;
}

function endGame() {
    document.getElementById("hit").style.display = "none";
    document.getElementById("stand").style.display = "none";
    document.getElementById("playAgain").style.display = "inline";
    document.getElementById("reset").style.display = "inline";

    document.getElementById("playAgain").addEventListener("click", gameRestart);
    document.getElementById("reset").addEventListener("click", reset);
}

function gameRestart() {

    document.getElementById("playAgain").style.display = "none";

    document.getElementById("reset").style.display = "none";

    dealerTotal = 0;
    dealerAce = 0;

    playerTotal = 0;
    playerAce = 0;

    document.getElementById("player-hand").innerText = "";
    document.getElementById('playerTotal').innerText = playerTotal;

    document.getElementById('dealerTotal').innerText = dealerTotal;

    if (document.getElementById("dealer-hand").hasChildNodes()) {
        while (document.getElementById("dealer-hand").lastChild.id !== "hiddenCard") {
            document.getElementById("dealer-hand")
                .removeChild(document.getElementById("dealer-hand").lastChild);
        }
    }

    if (purse === 0) {
        message = "You're out of money! Click 'Reset' to start over.";
        document.getElementById("playAgain").style.display = "none";
        document.getElementById("reset").style.display = "inline";
        document.getElementById("reset").addEventListener("click", reset);
    } else if (wager > purse) {
        message = "You don't have enough money to make that bet!";

        document.getElementById("wagerInput").style.display = "inline";
        document.getElementById("bet").style.display = "inline";
        document.getElementById("bet").addEventListener("click", bet);
    } else {
        purse -= wager;
        document.getElementById("purse").innerText = purse;
        document.getElementById("wager").innerText = wager;

        gameStart();
    }
    document.getElementById('results').innerText = message;
}

function reset() {
    location.reload();
}
