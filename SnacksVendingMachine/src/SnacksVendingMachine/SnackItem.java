package SnacksVendingMachine;

import java.util.Random;

public enum SnackItem {
	DORITOS("Doritos",1)
	,CHEETOS("Cheetos",1.5)
	,LAYS("Lays",1.5)
	,PRINGLES("Pringles",1)
	,GUM("gum",1);
    private String ItemName;
    private double price;
    SnackItem(String ItemName, double price) {
        this.ItemName = ItemName;
        this.price = price;
    }
    public String getItemName(){
        return ItemName;
    }
   
    public double getPrice(){
        return price;
    }
    
    public static SnackItem getRandomSnack() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}