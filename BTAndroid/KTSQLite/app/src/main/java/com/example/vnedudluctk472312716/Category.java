package com.example.vnedudluctk472312716;

public class Category {
    private int id;
    private String name;
    private int type; // 0: Chi, 1: Thu

    public Category(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getType() { return type; }
    
    @Override
    public String toString() {
        return name;
    }
}
