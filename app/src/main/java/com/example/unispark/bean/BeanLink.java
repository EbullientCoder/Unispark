package com.example.unispark.bean;

public class BeanLink {

    //Attributes
    private String linkName;
    private String linkAddress;

    //Methods
    //Constructor
    public BeanLink(String linkName, String linkAddress) {
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
