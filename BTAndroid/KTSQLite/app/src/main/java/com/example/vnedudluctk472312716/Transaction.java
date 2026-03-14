package com.example.vnedudluctk472312716;

public class Transaction {
    private int id;
    private double amount;
    private String date;
    private int catId;
    private int accId;
    private String catName; // Optional for display
    private String accName; // Optional for display

    public Transaction(int id, double amount, String date, int catId, int accId) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.catId = catId;
        this.accId = accId;
    }

    public int getId() { return id; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }
    public int getCatId() { return catId; }
    public int getAccId() { return accId; }
    
    public String getCatName() { return catName; }
    public void setCatName(String catName) { this.catName = catName; }
    
    public String getAccName() { return accName; }
    public void setAccName(String accName) { this.accName = accName; }
}
