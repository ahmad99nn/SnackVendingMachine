package SnacksVendingMachine;

public class Utilities {
	
	public static void fill2dArrayWithItems(int maxRows, int maxCol, int fullCapacity, SnackSlot snackSlots[][]) {
		for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCol; col++) {
            	snackSlots[row][col] = new SnackSlot(fullCapacity, SnackItem.getRandomSnack());
            	//System.out.print(snackSlots[row][col].getSnackItem().getItemName()+ ": " + row + "" + col + "  \t");
            }
            //System.out.println();
        }
	}
	public static boolean isSnackCodeWithinLimit(String rawSnackInput, int row, int col) {
		rawSnackInput = rawSnackInput.replaceAll(" ", "");
		
		try {
			int snackCode = Integer.valueOf(rawSnackInput);
		} catch(Exception e) {
			return false;
		}
		
		int length = rawSnackInput.length();
		
		if(length != 2)
			return false;
		
		int firstDigit = Integer.parseInt(String.valueOf(rawSnackInput.charAt(0)));
		if(!(firstDigit >= 0 && firstDigit <= row-1)) 
			return false;
		
		int secondDigit = Integer.parseInt(String.valueOf(rawSnackInput.charAt(1)));
		if(!(secondDigit >= 0 && secondDigit <= col-1)) 
			return false;
		
		return true;
	}

	public static String getMoneyTypes() {
		String string = "";
		MoneyType[] list = MoneyType.values();
		int length = list.length;
		int count = 0;
		
		for (MoneyType moneyType : MoneyType.values()) { 
			count++;
			
			if(count == length)
				string+=", or " + moneyType.getName();
			else if(count == 1)
				string+=" " + moneyType.getName();
			else
				string+=", " + moneyType.getName();
			
		}
		return string;
	}
}
