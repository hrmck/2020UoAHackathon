package com.example.inlocker;

public class StoreListItem {
    private String Name;    //all fields should match the fields in db

    public StoreListItem() {
        //empty constructor needed
    }

    public StoreListItem(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

}
