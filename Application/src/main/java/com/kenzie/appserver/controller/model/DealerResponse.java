package com.kenzie.appserver.controller.model;

public class DealerResponse {

    private boolean isBusted;
    private String name;


    public boolean isBusted() {
        return isBusted;
    }

    public void setBusted(boolean busted) {
        isBusted = busted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
