package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class run {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		List<Integer> listInt = new ArrayList<Integer>();
		listInt = generateNumber();
		String firstWord = "Pick a number between 10 - 20.";
		String processWord = "Processing ...";
		String pickWord = "Pick a number from most left or right";
		final int max = 20;
		final int min = 10;
		
		//Start first time
		System.out.println(firstWord);
		Scanner in = new Scanner(System.in);
		String strNumber = in.nextLine();
		int startNumber = 0;
		boolean notValid = false;
		
		try {
			startNumber = Integer.parseInt(strNumber);
			notValid = false;
		} catch (Exception e) {
			notValid = true;
		}

		while (notValid) {
	        System.out.println( "Try again, Number was input must between 10 - 20 or your was input not a number." ); 
	        String input = in.nextLine();
        	try {
        		startNumber = Integer.parseInt(input);
        		if (startNumber > max || startNumber < min) {
        			notValid = true;
				} else {
					notValid = false;
				}
			} catch (Exception e) {
				notValid = true;
			}
		}
		
		int sumUser = startNumber;
		int sumAuto = startNumber;
		
		for (int i = 0; i < 5; i++) {
			//Remove []
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < listInt.size(); j++) {
				sb.append(listInt.get(j) + " ");
			}
			
			System.out.println(processWord);
			System.out.println("List : " + " " +  (sb.toString()).trim());
			System.out.println(pickWord);
			
			String valueIn = in.nextLine();
			try {
				Integer.parseInt(valueIn);
				notValid = false;
			} catch (Exception e) {
				notValid = true;
			}
			
			while (notValid) {
				System.out.println("Try again, Number was input not valid, must between 10 -20 or not in list number, \r\n"
						+ "or you choosen number not from most left or right, or you input not number." ); 
				valueIn = in.nextLine();
		        try {
		        	int bil = Integer.parseInt(valueIn);
		        	if (bil > max || !listInt.contains(bil)
					    || (bil != listInt.get(0) && bil != listInt.get(listInt.size() - 1))) {
		        		notValid = true;
					} else {
						notValid = false;
					}
				} catch (Exception e) {
					notValid = true;
				}
			}		
			
			//User
			sumUser = sumUser + Integer.parseInt(valueIn);			
			int val = Integer.parseInt(valueIn);
			if (val == listInt.get(0)) {
				listInt.remove(0);
			} else if (val == listInt.get(listInt.size() - 1)) {
				listInt.remove(listInt.size() - 1);
			}
			
			//Auto
			List<Integer> randomInt = new ArrayList<Integer>();
			if (listInt.size() >= 2) {
				randomInt.add(0);
				randomInt.add(listInt.size() - 1);
			}
			
			int autoChoosen = 0;
			if (listInt.size() == 1) {
				autoChoosen = 0;
			} else {
				autoChoosen = getRandomElement(randomInt);
			}
			
			int numberAuto = listInt.get(autoChoosen);
			sumAuto = sumAuto + numberAuto;
			if (numberAuto == listInt.get(0)) {
				listInt.remove(0);
			} else if (numberAuto == listInt.get(listInt.size() - 1)){
				listInt.remove(listInt.size() - 1);
			}
			
			System.out.println("Sum User : " + sumUser);
			System.out.println("Sum AI : " + sumAuto);
			
			//In last loop
			if (i == (5 - 1)) {
				String result = "";
				if (sumUser > sumAuto) {
					result = "you win.";
				} else if (sumUser < sumAuto) {
					result = "you lose.";
				} else {
					result = "draw.";
				}
				System.out.println("Game over, " + result);
			}
		}
	}
	
	//Generate random numbers between 10 to 20
	public static List<Integer>generateNumber () {
		int min = 10;
		int max = 20;
		int range = max - min + 1;

		//Generate random numbers between 10 to 20
		List<Integer> listInt = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			int random = (int) ((Math.random() * range) + min);
			listInt.add(random);
		}
		return listInt;
	}
	
	// Function select an element base on index  
    // and return an element 
    public static int getRandomElement(List<Integer> list) 
    { 
        Random rand = new Random(); 
        return list.get(rand.nextInt(list.size())); 
    } 
}
