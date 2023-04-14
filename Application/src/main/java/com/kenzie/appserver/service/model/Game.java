package com.kenzie.appserver.service.model;

import java.time.LocalDateTime;
import java.util.List;

public class Game {
    private String gameId;
    private final Deck deck;
    private List<Player> players;
    private final Dealer dealer;
    private boolean gameOver;
    private String playerId;
    private int score;
    private LocalDateTime timestamp;

    public Game(String gameId, String playerId) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.dealer = new Dealer();
        this.deck = new Deck();
        this.gameOver = false;
    }

    public Game(String gameId) {
        this.gameId = gameId;
        this.dealer = new Dealer();
        this.deck = new Deck();
        this.gameOver = false;
    }

    public String getGameId() {
        return gameId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void dealCards() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.dealCard(deck.drawCard());
            }
            dealer.dealCard(deck.drawCard());
        }
    }

    public void play() {
        for (Player player : players) {
            while (player.shouldHit() && !gameOver) {
                player.dealCard(deck.drawCard());
                checkIfPlayerBusted(player);
            }
        }

        while (dealer.getTotalPoints() < 17 && !gameOver) {
            dealer.dealCard(deck.drawCard());
            checkIfDealerBusted();
        }

        checkIfGameEnded();
        determineWinners();
    }

    private void checkIfPlayerBusted(Player player) {
        if (player.getTotalPoints() > 21) {
            player.setBusted();
        }
    }

    private void checkIfDealerBusted() {
        if (dealer.getTotalPoints() > 21) {
            dealer.setBusted();
        }
    }

    private void checkIfGameEnded() {
        boolean allPlayersStand = players.stream().allMatch(Player::isStanding);
        boolean allPlayersBusted = players.stream().allMatch(Player::isBusted);
        gameOver = allPlayersStand || allPlayersBusted || dealer.isBusted();
    }

    private void determineWinners() {
        for (Player player : players) {
            if (!player.isBusted() && !dealer.isBusted()) {
                if (player.getTotalPoints() > dealer.getTotalPoints()) {
                    player.win();
                } else if (player.getTotalPoints() == dealer.getTotalPoints()) {
                    player.tie();
                } else {
                    player.lose();
                }
            } else if (player.isBusted()) {
                player.lose();
            } else {
                player.win();
            }
        }
    }

    public boolean isGameOver() {
        for (Player player : players) {
            if (!player.isStanding() && !player.isBusted()) {
                return false;
            }
        }
        return true;
    }

    public Player getPlayerById(String playerId) {
        for (Player player : players) {
            if (player.getName().equals(playerId)) {
                return player;
            }
        }
        return null;
    }

    public boolean placeBet(String playerId, int betAmount) {
        Player player = getPlayerById(playerId);
        if (player != null && player.getBalance() >= betAmount) {
            player.setBetAmount(betAmount);
            player.withdraw(betAmount);
            return true;
        } else {
            return false;
        }
    }

    public Card dealCard(String playerId) {
        Player player = getPlayerById(playerId);
        if (player != null) {
            Card card = deck.drawCard();
            player.dealCard(card);
            return card;
        } else {
            return null;
        }
    }

    public void stand(String playerId) {
        Player player = getPlayerById(playerId);
        if (player != null && !player.isBusted()) {
            player.setStanding();
        }
    }
}