package com.example.inlocker;

public class ProductListItem {
    private String Name;
    private int Price;
    private int Amount;
    private String Category;
    private String itemImageUrl;

    public ProductListItem() {
        //empty constructor needed
    }

    public ProductListItem(String Name, int Price, int Amount, String Category, String itemImageUrl) {
        this.Name = Name;
        this.Price = Price;
        this.Amount = Amount;
        this.Category = Category;
        this.itemImageUrl = itemImageUrl;
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

    public String getItemImageUrl() {   //on the roadmap
        return itemImageUrl;
    }
}
