package com.example.inlocker;

public class ProductListItem {
    private String Name;
    private int Price;
    private int Amount;
    private String Category;

    public ProductListItem() {
        //empty constructor needed
    }

    public ProductListItem(String Name, int Price, int Amount, String Category) {
        this.Name = Name;
        this.Price = Price;
        this.Amount = Amount;
        this.Category = Category;
    }

    public String getName() {
        return Name;
    }

    public int getPrice() {
        return Price;
    }

    public int getAmount() {
        return Amount;
    }

    public String getItemCategory() {
        return Category;
    }
}
