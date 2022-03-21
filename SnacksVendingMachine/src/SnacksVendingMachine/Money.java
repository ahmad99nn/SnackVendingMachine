package SnacksVendingMachine;

public enum Money{
	TEN_CENTS("10c", 0.10, MoneyType.COIN), 
	TWENTY_CENTS("20c", 0.20, MoneyType.COIN), 
	FIFTY_CENTS("50c", 0.5, MoneyType.COIN), 
	ONE_DOLLAR("1$", 1, MoneyType.COIN),

	TWENTY_DOLLARS("20$", 20, MoneyType.NOTE),
	FIFTY_DOLLARS("50$", 50, MoneyType.NOTE);
	
	private String denomination;
	private double value;
	private MoneyType type;

	private Money(String denomination, double value, MoneyType type){
		this.denomination = denomination;
		this.value = value;
		this.type = type;
	}

	public String getDenomination() {
		return denomination;
	}

	public double getValue() {
		return value;
	}

	public MoneyType getType() {
		return type;
	}
	
	
}
