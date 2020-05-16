package com.example.inlocker;

public class StoreListItem {
    private String Name;    //all fields should match the fields in db
    private String logoImageLink;

    public StoreListItem() {
        //empty constructor needed
    }

    public StoreListItem(String Name, String logoImageLink) {
        this.Name = Name;
        this.logoImageLink = logoImageLink;
    }

    public String getName() {
        return Name;
    }

    public String getLogoImageLink() {
        return logoImageLink;
    }
}
