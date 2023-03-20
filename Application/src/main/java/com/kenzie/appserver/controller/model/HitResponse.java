package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Card;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HitResponse {

    @JsonProperty("cardDealt")
    private Card cardDealt;

    @JsonProperty("message")
    private String message;

    public HitResponse(Card cardDealt, String message) {
        this.cardDealt = cardDealt;
        this.message = message;
    }

    public Card getCardDealt() {
        return cardDealt;
    }

    public void setCardDealt(Card cardDealt) {
        this.cardDealt = cardDealt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
