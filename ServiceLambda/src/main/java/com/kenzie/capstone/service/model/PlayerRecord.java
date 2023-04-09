//package com.kenzie.capstone.service.model;
//
//import com.amazonaws.services.dynamodbv2.datamodeling.*;
//import com.kenzie.appserver.service.model.Card;
//
//import java.util.List;
//import java.util.Objects;
//
//@DynamoDBTable(tableName = "Players")
//public class PlayerRecord {
//    private String playerId;
//    private String name;
//    private String email;
//    private int balance;
//    private List<Card> cards;
//    private int totalPoints;
//    private boolean isBusted;
//    private boolean isStanding;
//    private String result;
//
//
//    @DynamoDBHashKey(attributeName = "playerId")
//    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "BalanceIndex")
//    public String getPlayerId() {
//        return playerId;
//    }
//
//    public void setPlayerId(String playerId) {
//        this.playerId = playerId;
//    }
//
//    @DynamoDBIndexHashKey(globalSecondaryIndexName = "NameIndex", attributeName = "name")
//    public String getName() {
//        return name;
//    }
//
//    @DynamoDBIndexHashKey(globalSecondaryIndexName = "BalanceIndex", attributeName = "balance")
//    public int getBalance() {
//        return balance;
//    }
//
//    public void setBalance(int balance) {
//        this.balance = balance;
//    }
//
//    @DynamoDBAttribute(attributeName = "email")
//    public String getEmail() {
//        return email;
//    }
//
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setBetAmount(String name) {
//        this.name = name;
//    }
//
//    public List<Card> getCards() {
//        return cards;
//    }
//
//    public void setCards(List<Card> cards) {
//        this.cards = cards;
//    }
//
//    public int getTotalPoints() {
//        return totalPoints;
//    }
//
//    public void setTotalPoints(int totalPoints) {
//        this.totalPoints = totalPoints;
//    }
//
//    public boolean isBusted() {
//        return isBusted;
//    }
//
//    public void setBusted(boolean busted) {
//        isBusted = busted;
//    }
//
//    public boolean isStanding() {
//        return isStanding;
//    }
//
//    public void setStanding(boolean standing) {
//        isStanding = standing;
//    }
//
//    public String getResult() {
//        return result;
//    }
//
//    public void setResult(String result) {
//        this.result = result;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        PlayerRecord playerRecord = (PlayerRecord) o;
//        return Objects.equals(playerId, playerRecord.playerId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(playerId);
//    }
//}
