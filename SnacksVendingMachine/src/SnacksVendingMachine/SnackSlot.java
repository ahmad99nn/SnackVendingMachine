package SnacksVendingMachine;

public class SnackSlot {
	private int availableQuantity;
	private SnackItem SnackItem;

	public SnackSlot(int availableQuantity, SnackItem SnackItem) {
		this.availableQuantity = availableQuantity;
		this.SnackItem = SnackItem;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public void substractItemQuantity() throws Exception {//come back to later
		if(!isEmpty())
			availableQuantity--;
		else
			throw new Exception();//come back to later
	}
	public boolean isEmpty() {
		if(availableQuantity == 0)
			return true;
		else
			return false;
	}

	public SnackItem getSnackItem() {
		return SnackItem;
	}

	public void setSnackItem(SnackItem snackItem) {
		SnackItem = snackItem;
	}

}