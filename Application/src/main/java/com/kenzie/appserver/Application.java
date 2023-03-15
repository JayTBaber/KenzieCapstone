package com.kenzie.appserver;

import com.kenzie.appserver.dao.CardDAO;
import com.kenzie.appserver.service.model.Card;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import static java.lang.System.exit;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class Application {
    public static void main(String[] args) {

//        SpringApplication.run(Application.class, args);
        CardDAO deck = new CardDAO();
        Scanner scanner = new Scanner(System.in);

        int playerScore = 0;
        Card playerCard = deck.drawCard();

        Collection<Card> playerCards = new ArrayList<>();
        playerCards.add(playerCard);
        playerCards.add(deck.drawCard());

        int playerCardValue = playerCards.stream()
                .mapToInt(card -> card.getRank().getValue())
                .sum();

        System.out.println("Player's cards: " + playerCards);
        playerScore = playerCardValue;
        System.out.println("Player's score: " + playerScore);

        int dealerScore = 0;
        Card dealerCard = deck.drawCard();
        Collection<Card> dealerCards = new ArrayList<>();
        dealerCards.add(dealerCard);
        dealerCards.add(deck.drawCard());

        while (playerScore < 21) {
            System.out.println("Would you like to hit or stay?");
            String hitOrStay = scanner.nextLine();
            if (hitOrStay.equals("hit") || hitOrStay.equals("h")) {
                Card newCard = deck.drawCard();
                int updatedPlayerValue = newCard.getRank().getValue();
                playerCards.add(newCard);
                playerScore += updatedPlayerValue;
                System.out.println("Player's cards: " + playerCards);
                System.out.println("Player's score: " + playerScore);
            } else {
                break;
            }
        }

        if (playerScore == 21) {
            System.out.println("Blackjack!");
        }

        if (playerScore > 21) {
            System.out.println("Player busts! Dealer wins!");
            System.out.println("Would you like to play again?");
            String playAgain = scanner.nextLine();
            if (playAgain.equals("yes") || playAgain.equals("y")) {
                main(args);
            } else {
                System.out.println("Thanks for playing!");
                exit(0);
            }
        }

        System.out.println("Dealer's cards: " + dealerCards);
        dealerScore = dealerCards.stream()
                .mapToInt(card -> card.getRank().getValue())
                .sum();
        System.out.println("Dealer's score: " + dealerScore);

        while (dealerScore < 16) {
            Card newCard = deck.drawCard();
            int updatedDealerValue = newCard.getRank().getValue();
            dealerCards.add(newCard);
            dealerScore += updatedDealerValue;
            System.out.println("Dealer's cards: " + dealerCards);
            System.out.println("Dealer's score: " + dealerScore);
        }

        if (dealerScore == 21) {
            System.out.println("Blackjack!");
        }

        if (dealerScore > 21) {
            System.out.println("Dealer busts! Player wins!");
        }

        if (playerScore > dealerScore) {
            System.out.println("Player wins!");
        } else if (playerScore < dealerScore) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a push!");
        }

        System.out.println("Would you like to play again?");
        String playAgain = scanner.nextLine();
        if (playAgain.equals("yes") || playAgain.equals("y")) {
            main(args);
        } else {
            System.out.println("Thanks for playing!");
        }
    }
}
