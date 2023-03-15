package com.kenzie.appserver.dao;

import com.kenzie.appserver.enums.Rank;
import com.kenzie.appserver.enums.Suit;
import com.kenzie.appserver.service.model.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDAO {
    private final List<Card> cards;

    public CardDAO() {
        this.cards = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.remove(0);
    }

    public List<Card> findAll() {
        return cards;
    }

    public Card save(Card card) {
        cards.add(card);
        return card;
    }

    public void delete(Card card) {
        cards.remove(card);
    }

    public Card findById(long id) {
        for (Card card : cards) {
            if (card.getId() == id) {
                return card;
            }
        }
        return null;
    }
}
