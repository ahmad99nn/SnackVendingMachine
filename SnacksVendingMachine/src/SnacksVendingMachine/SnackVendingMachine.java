package SnacksVendingMachine;

import java.util.Scanner;

public class SnackVendingMachine {
	private static final int ROWS = 5;
	private static final int COLUMNS = 5;

	private static final int SNACK_SLOT_CAPACITY = 10;

	private static SnackSlot snackSlots[][] = new SnackSlot [ROWS][COLUMNS];

	public void initilization() {
		Utilities.fill2dArrayWithItems(ROWS, COLUMNS, SNACK_SLOT_CAPACITY, snackSlots);
		//showMainMenu();
		//choosePaymentMethod();

	}
	//An "(X)" near a snack slot indicates that it is empty
	public void showMainMenu() {
		snackSlots[1][2].setAvailableQuantity(0);//to showcase what
		for (int row = 0; row < ROWS; row++) {   //an empty slot would look like
			for (int col = 0; col < COLUMNS; col++) {
				SnackSlot snackSlot = snackSlots[row][col];
				String printString = snackSlot.getSnackItem().getItemName()+ ": " + row + "" + col;
				if(snackSlot.isEmpty())
					System.out.print(printString + "(X)\t");
				else
					System.out.print(printString + "  \t");
			}
			System.out.println("");
		}
		checkSnack();
	}

	private void checkSnack() {
		boolean keepGoing = true;
		boolean incorrectPreviousAttempt = false;

		String snackNumber;

		while(keepGoing) {

			if(incorrectPreviousAttempt) 
				System.out.print("Please enter a valid snack code:");
			else
				System.out.print("Please enter snack code:");

			//int snackCode= 0;//No snack has this code

			Scanner in = new Scanner(System.in);
			String rawSnackCodeInput = in .nextLine();
			//System.out.println(rawSnackCodeInput);
			if(Utilities.isSnackCodeWithinLimit(rawSnackCodeInput, ROWS, COLUMNS)) {
				if(!isSnackSlotAvailable(rawSnackCodeInput)) {					
					System.out.println("This snack slot is empty!");
					keepGoing = true;

				}
				else {
					keepGoing = false;
					snackNumber = (String.valueOf(rawSnackCodeInput.replaceAll(" ", "")));
					choosePaymentMethod(snackNumber);
				}
			}
			else {
				keepGoing = true;
				incorrectPreviousAttempt = true;
			}
			// System.out.println(snackCode);
		}

	}

	private void choosePaymentMethod(String snackNumber) {
		boolean keepGoing = true;
		boolean incorrectPreviousAttempt = false;

		String moneyTypeString = Utilities.getMoneyTypes();
		while(keepGoing) {
			if(incorrectPreviousAttempt) 
				System.out.print("Please enter a valid payment method:" + moneyTypeString+ " or 'exit' to go back");
			else
				System.out.print("Please enter payment method:" + moneyTypeString+ " or 'exit' to go back");

			Scanner in = new Scanner(System.in);
			String paymentMethod = in.nextLine().replaceAll(" ", "").toLowerCase();
			//System.out.println(paymentMethod);

			keepGoing = false;

			if(paymentMethod.equals("coin") || paymentMethod.equals("note") 
					|| paymentMethod.equals("card")) {
				SnackSlot snackSlot  = getSnackSlot(snackNumber);
				if(paymentMethod.equals("coin")) {
					payByCoins(snackSlot, snackNumber);
				}
				else if(paymentMethod.equals("note")) {
					payByNotes(snackSlot, snackNumber);
				}
				else if(paymentMethod.equals("card")) {
					payByCard(snackSlot, snackNumber);
				}
			}
			else if(paymentMethod.equals("exit")) {
				showMainMenu();
			}

			else {
				keepGoing = true;
				incorrectPreviousAttempt = true;
			}
		}

	}

	private void payByCoins(SnackSlot snackSlot, String snackNumber) {
		boolean keepGoing = true;

		double sum = 0;
		//find price of item
		SnackItem snackItem = snackSlot.getSnackItem();

		double itemPrice = snackItem.getPrice();

		System.out.println("To buy " + snackItem.getItemName() + " you have to pay " + itemPrice + "$");

		while(keepGoing) {
			System.out.print("Please enter one of these coins: 10c 20c 50c 1$ or 'exit' to go back");

			Scanner in = new Scanner(System.in);
			String choice = in.nextLine().replaceAll(" ", "").toLowerCase();

			if (choice.equals("10c")) {
				sum += Money.TEN_CENTS.getValue();
				System.out.println("You payed 10c");
				System.out.println("Sum paid: " + sum);
			} else if (choice.equals("20c")) {
				sum += Money.TWENTY_CENTS.getValue();
				System.out.println("You payed 20c");
				System.out.println("Sum paid: " + sum);
			} else if (choice.equals("50c")) {
				sum += Money.FIFTY_CENTS.getValue();
				System.out.println("You payed 50c");
				System.out.println("Sum paid: " + sum);
			} else if (choice.equals("1$")) {
				sum += Money.ONE_DOLLAR.getValue();
				System.out.println("You payed 1$");
				System.out.println("Sum paid: " + sum);
			}
			else if(choice.equals("exit")) {
				choosePaymentMethod(snackNumber);
			}
			else {
				System.out.println("Please enter a valid coin!");
			}

			if(sum >= itemPrice) {
				System.out.println("You successfully bought the snack.");
				giveBackChange(snackSlot, itemPrice, sum);
				try {
					snackSlot.substractItemQuantity();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//					e.printStackTrace();
				}
				showMainMenu();
				keepGoing = false;
				break;
			}
		}

	}

	private void payByNotes(SnackSlot snackSlot, String snackNumber) {
		boolean keepGoing = true;

		double sum = 0;
		//find price of item
		SnackItem snackItem = snackSlot.getSnackItem();

		double itemPrice = snackItem.getPrice();

		int[] denominationsInserted = new int [2];

		System.out.println("To buy " + snackItem.getItemName() + " you have to pay " + itemPrice + "$");
		while(keepGoing) {
			System.out.print("Please enter one of these notes: 20$ 50$ or 'exit' to go back");

			Scanner in = new Scanner(System.in);
			String choice = in.nextLine().replaceAll(" ", "").toLowerCase();
			if (choice.equals("20$")) {
				sum += Money.TWENTY_DOLLARS.getValue();
				System.out.println("You payed 20$");
			} else if (choice.equals("50$")) {
				sum += Money.FIFTY_DOLLARS.getValue();
				System.out.println("You payed 50$");
			} 
			else if(choice.equals("exit")) {
				choosePaymentMethod(snackNumber);
			}
			else {
				System.out.println("Please enter a valid coin!");
			}

			if(sum >= itemPrice) {
				System.out.println("You successfully bought the snack.");
				giveBackChange(snackSlot, itemPrice, sum);
				try {
					snackSlot.substractItemQuantity();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//					e.printStackTrace();
				}
				showMainMenu();
				keepGoing = false;
				break;
			}
		}
	}

	private void payByCard(SnackSlot snackSlot, String snackNumber) {
		System.out.println("You successfully bought the snack.");

		try {
			snackSlot.substractItemQuantity();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//					e.printStackTrace();
		}
		showMainMenu();
	}

	private void giveBackChange(SnackSlot snackSlot, double itemPrice, double sumPaid) {
		double leftToReturn = sumPaid - itemPrice;

		int[] chargeByDenomination = new int[5];
		chargeByDenomination[0] = 0;//10c
		chargeByDenomination[1] = 0;//20c
		chargeByDenomination[2] = 0;//50C
		chargeByDenomination[3] = 0;//1$
		chargeByDenomination[4] = 0;//20$

		while(leftToReturn > 0) {
			if(leftToReturn >= Money.TWENTY_DOLLARS.getValue()) {
				leftToReturn-=Money.TWENTY_DOLLARS.getValue();
				chargeByDenomination[4]++;
			}
			else if(leftToReturn >= Money.ONE_DOLLAR.getValue()) {
				leftToReturn-=Money.ONE_DOLLAR.getValue();
				chargeByDenomination[3]++;
			}
			else if(leftToReturn >= Money.FIFTY_CENTS.getValue()) {
				leftToReturn-=Money.FIFTY_CENTS.getValue();
				chargeByDenomination[2]++;
			}
			else if(leftToReturn >= Money.TWENTY_CENTS.getValue()) {
				leftToReturn-=Money.TWENTY_CENTS.getValue();
				chargeByDenomination[1]++;
			}
			else if(leftToReturn >= Money.TEN_CENTS.getValue()) {
				leftToReturn-=Money.TEN_CENTS.getValue();
				chargeByDenomination[0]++;
			}
			else {
				System.out.println("Ther was a problem. "
						+ "Please contact the maintenance at their number.");
				break;
			}
		}
		System.out.println("Charge to return is: " + (sumPaid - itemPrice) + ". " + 
				chargeByDenomination[4] + ": 20$, " + chargeByDenomination[3]
						+ ": 1$, " + chargeByDenomination[2] + ": 50c, " 
						+ chargeByDenomination[1] + ": 20c, "
						+ chargeByDenomination[0] + ": 10c");
	}

	private static SnackSlot getSnackSlot(String snackNumber) {
		String firstDigit =  snackNumber.substring(0, 1);
		String secondDigit = snackNumber.substring(1, 2);

		return snackSlots[Integer.valueOf(firstDigit)][Integer.valueOf(secondDigit)];
	}

	private static boolean isSnackSlotAvailable(String rawSnackCodeInput) {
		int firstDigit = Integer.parseInt(String.valueOf(rawSnackCodeInput.charAt(0)));
		int secondDigit = Integer.parseInt(String.valueOf(rawSnackCodeInput.charAt(1)));

		if(snackSlots[firstDigit][secondDigit].isEmpty()) 
			return false;
		else
			return true;

	}


}
