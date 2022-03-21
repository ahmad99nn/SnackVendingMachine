package SnacksVendingMachine;

public enum MoneyType{
	COIN("coin"),
	NOTE("note"),
	CARD("card");
	
	private String name;
	
	private MoneyType(String name){
		this.name = name;

	}

	public String getName() {
		return name;
	}

}
