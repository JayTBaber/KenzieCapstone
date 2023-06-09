package com.kenzie.appserver.service;

import com.kenzie.appserver.dao.CardDAO;
import com.kenzie.appserver.service.model.Card;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private CardDAO cardDAO;

    public List<Card> getAllCards() {
        return cardDAO.findAll();
    }

    public Card getCardById(long id) throws EntityNotFoundException {
        Optional<Card> optionalCard = Optional.ofNullable(cardDAO.findById(id));
        if (optionalCard.isPresent()) {
            return optionalCard.get();
        } else {
            throw new EntityNotFoundException("Card not found with id: " + id);
        }
    }

    public Card saveCard(Card card) {
        return cardDAO.save(card);
    }

    public void deleteCard(Card card) {
        cardDAO.delete(card);
    }
}
