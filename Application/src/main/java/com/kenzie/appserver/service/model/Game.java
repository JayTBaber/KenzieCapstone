package com.kenzie.appserver.service.model;

import java.util.List;

public class Game {
    private final Deck deck;
    private final List<Player> players;
    private final Dealer dealer;

    public Game(List<Player> players) {
        this.players = players;
        this.dealer = new Dealer();
        this.deck = new Deck();
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
            while (player.shouldHit()) {
                player.dealCard(deck.drawCard());
            }
        }

        while (dealer.getTotalPoints() < 17) {
            dealer.dealCard(deck.drawCard());
        }

        for (Player player : players) {
            if (player.getTotalPoints() <= 21 && (player.getTotalPoints() > dealer.getTotalPoints() || dealer.getTotalPoints() > 21)) {
                player.win();
            } else {
                player.lose();
            }
        }
    }
}
