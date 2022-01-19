package com.example.unispark.database.others.provaDB;

public class LinkModel {

    //Attributes
    private String linkName;
    private String linkAddress;

    //Methods
    //Constructor
    public LinkModel(String linkName, String linkAddress) {
        this.linkName = linkName;
        this.linkAddress = linkAddress;
    }

    //Getter
    public String getLinkName() {
        return linkName;
    }
    public String getLinkAddress() {
        return linkAddress;
    }

    //Setter
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }
    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }
}

