package com.example.common.model;



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

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }
}

