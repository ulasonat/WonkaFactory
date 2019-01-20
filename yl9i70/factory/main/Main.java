package factory.main;

import factory.model.GoldenTicket;
import factory.model.OompaLoompaSong;
import factory.model.Being;
import factory.model.Kid;
import factory.model.OompaLoompa;
import factory.model.Product;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.text.ParseException;

public class Main {
	
	private static Scanner scanner = new Scanner(System.in);
	private static File beingsFile = new File("files/beingsList.txt");
	private static File productsFile = new File("files/productsList.txt");
	private static ArrayList<Being> beings = new ArrayList<>();
	private static ArrayList<Product> products = new ArrayList<>();
	
	public static void main(String[] args) {
		
		prePopulate();
		
		boolean quit = false;
		
		displayMenu();
		
		while(!quit) {
			System.out.print("Type your command: ");
			switch(scanner.nextInt()) {
				case 0:
				displayMenu();
				break;
				case 1:
				registerPrizeTickets();
				break;
				case 2:
				listAllPrizeTickets();
				break;
				case 3:
				listOnlyRaffledTickets();
				break;
				case 4:
				createANewOompaLoompaSong();
				break;
				case 5:
				registerBeings();
				break;
				case 6:
				registerProducts();
				break;
				case 7:
				ruffleTickets();
				break;
				case 8:
				registerSale();
				break;
				case 9:
				listWinners();
				break;
				case 10:
				System.out.println("Thank you for visiting our factory.");
				quit = true;
				break;
				default:
				break;
			}
		}
	}
	
	private static void displayMenu() {
		System.out.println("Press: \n\t" +
							"0 - display the menu\n\t" + 
							"1 - register prize tickets\n\t" +
							"2 - list all prize tickets\n\t" +
							"3 - list only raffled tickets\n\t" +
							"4 - create a new oompa loompa song\n\t" +
							"5 - register beings\n\t" +
							"6 - register products\n\t" +
							"7 - ruffle tickets\n\t" +
							"8 - register sale\n\t" +
							"9 - list winners\n\t" + 
							"10 - quit");
	}
	
	private static void registerPrizeTickets() {
		
		System.out.print("How many prize tickets do you want to register: ");
		int input = scanner.nextInt();
		scanner.nextLine();
		
		for(int i=0; i<input; i++) {
			
			try {
		
			System.out.print("Type the serial number of product that you'd like to register the prize ticket to: ");
			String serialNumber = scanner.nextLine();
		
			boolean isFound = false;
			Product product = null;
			
			for(Product p : products) {
				if(p.getSerialNumber().equals(serialNumber)) {
					product = p;
					isFound = true;
					break;
				}
			}
		
			if(!isFound) {
				System.out.println("There is no such a product with that serial number, registration of this prize ticket unsuccessful.");
				continue;
			}
			
			System.out.print("Do you want to register a golden ticket(Y/N)?: ");
			String yesOrNo;
				do {
					yesOrNo = scanner.nextLine();
				} while(!yesOrNo.equalsIgnoreCase("y") && !yesOrNo.equalsIgnoreCase("n"));
				
			if(yesOrNo.equalsIgnoreCase("n")) {
				product.setPrizeTicket(null);
			} else {
			
				System.out.print("Type the code of the golden ticket: ");
				String code = scanner.nextLine();
			
				System.out.print("Type the raffle date of the golden ticket (yyyy-MM-dd HH:mm): ");
				String date = scanner.nextLine();
			
				product.setPrizeTicket(new GoldenTicket(code, date));
			}
			
			System.out.println("Registration of this prize ticket successful.");
			
			updateProductsFile();
			
			} catch(Exception e) {
				System.out.println("There was a problem with getting the input, registration of this prize ticket unsuccessful.");
			}
		
		}
	}
	
	private static void listAllPrizeTickets() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		int counter = 0;
		for(Product p : products) {
			System.out.print(String.valueOf(++counter) + ") The product with the serial number " + p.getSerialNumber());
			if(p.getPrizeTicket() != null) {
				System.out.println(" contains a golden ticket which is:");
				System.out.println("Code of the golden ticket: " + p.getPrizeTicket().getCode());
				System.out.println("Raffle date of the golden ticket: " + dt.format(p.getPrizeTicket().getRaffleDate()));
			} else {
				System.out.println(" does not contain a golden ticket.");
			}
		}
		if(counter == 0)
			System.out.println("There is no prize ticket.");
	}
	
	private static void listOnlyRaffledTickets() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		int counter = 0;
		for(Product p : products) {
			GoldenTicket tmp_ticket = p.getPrizeTicket();
			if(tmp_ticket != null && tmp_ticket.isRaffled()) {
				System.out.println(String.valueOf(++counter) + ") The product with the serial number " + p.getSerialNumber() + " has a raffled ticket which is:");
				System.out.println("Code of the golden ticket: " + tmp_ticket.getCode());
				System.out.println("Raffle date of the golden ticket: " + dt.format(tmp_ticket.getRaffleDate()));
			}
		}
		if(counter == 0)
			System.out.println("There is no raffled ticket.");
	}
	
	private static void createANewOompaLoompaSong() {
		System.out.print("How many lines do you want the song contain: ");
		System.out.println(new OompaLoompaSong(scanner.nextInt()).sing());
	}
	
	private static void registerBeings() {
		
		System.out.print("How many being do you want to register: ");
		int input = scanner.nextInt();
		
		scanner.nextLine(); // clearing the buffer
		
		for(int i=0; i<input; i++) {
		String typeOfBeing;
		
		do {
			System.out.print("Please type if you want to register a kid or an oompa loompa: ");
			typeOfBeing = scanner.nextLine();
		} while(!typeOfBeing.equalsIgnoreCase("kid") && !typeOfBeing.equalsIgnoreCase("oompa loompa"));
		
		Being being;
		
		try {
		
		if(typeOfBeing.equalsIgnoreCase("kid")) {
			
			System.out.print("Type the name of the kid: ");
			String name = scanner.nextLine();
			
			System.out.print("Type the birthday of the kid (yyyy-MM-dd): ");
			String birthday = scanner.nextLine();
			
			System.out.print("Type the place of birth of the kid: ");
			String placeOfBirth = scanner.nextLine();
			
			
			being = new Kid(name, birthday, placeOfBirth);
			
			
		} else {
			
			System.out.print("Type the name of the oompa loompa: ");
			String name = scanner.nextLine();
			
			System.out.print("Type the height of the oompa loompa(cm): ");
			int height = scanner.nextInt();
			scanner.nextLine();
			
			System.out.print("Type the favorite food of the oompa loompa: ");
			String favoriteFood = scanner.nextLine();
			
			being = new OompaLoompa(name, height, favoriteFood);
		}
		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("There was a problem with the input, adding a being is not completed!");
			continue;
		}
		
		beings.add(being);
		
		try {
			updateBeingsFile(); // adds being to the file.
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("There was a problem with the writing to the file, adding a being is not completed!");
		}
		
		System.out.println("The being succesfully added.");
		
		}
			
	}
	
	private static void registerProducts() {
		System.out.print("How many products do you want to register: ");
		int input = scanner.nextInt();
		
		scanner.nextLine(); // clearing the buffer
		
		for(int i=0; i<input; i++) {
			
			Product product;
			
			try {
				System.out.print("Type the description of the product: ");
				String description = scanner.nextLine();
			
				System.out.print("Type the barcode of the product: ");
				long barcode = scanner.nextLong();
				scanner.nextLine();
			
				System.out.print("Type the serial number of the product: ");
				String serialNumber = scanner.nextLine();
				
				System.out.print("Does it have a golden ticket(Y/N)?: ");
				
				String yesOrNo;
				do {
					yesOrNo = scanner.nextLine();
				} while(!yesOrNo.equalsIgnoreCase("y") && !yesOrNo.equalsIgnoreCase("n"));
				
				if(yesOrNo.equalsIgnoreCase("n")) {
					product = new Product(description, barcode, serialNumber);
				} else {
					
					System.out.print("Type the code of the golden ticket: ");
					String code = scanner.nextLine();
				
					System.out.print("Type the raffle date of the golden ticket (yyyy-MM-dd HH:mm): ");
					String date = scanner.nextLine();
					
					product = new Product(description, barcode, serialNumber, code, date); // using one of the overloaded constructors of Product class
				}
				
			
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("There was a problem with the input, adding a being is not completed!");
				continue;
			}
			
			products.add(product);
			
			try {
				updateProductsFile();
			} catch(IOException e) {
				e.printStackTrace();
				System.out.println("There was a problem with the writing to the file, adding a product is not completed!");
			}
		
		System.out.println("The product succesfully added.");
			
		}
		
		
	}
	
	private static void ruffleTickets() {
		System.out.print("How many tickets do you want to be ruffled: ");
		int n = scanner.nextInt();
		ArrayList<GoldenTicket> ticketsToBeRuffled = new ArrayList<GoldenTicket>();
		
		for(int i=0; i<n; i++) {
			ticketsToBeRuffled.add(GoldenTicket.createRandom());
		}
		
		for(int i=0; i<n; i++) {
			int indexOfProduct = new Random().nextInt(products.size());
			products.get(indexOfProduct).setPrizeTicket(ticketsToBeRuffled.get(i));
		}
		
		try {
			updateBeingsFile();
			updateProductsFile();
		} catch(IOException e) {
			e.printStackTrace();
			return;
		}
		
		System.out.println("Tickets have been ruffled.");
		
	}
	
	private static void registerSale() {
		
		// asking for code of the kid:
		int kidCode;
		Being kid = null;
		boolean contains = false;
		
		do {
			System.out.print("Please type the code of the kid: ");
			kidCode = scanner.nextInt();
			for(Being b : beings) {
				if(b instanceof Kid && b.getCode() == kidCode) {
					kid = b;
					contains = true;
					break;
				}
			}
			if(!contains)
				System.out.println("There's no kid with that code!");
		} while(!contains);
		
		
		// asking for the product barcode:
		long barcode;
		ArrayList<Product> productsWithSameBarcode = new ArrayList<Product>();
		contains = false;
		
		do {
			System.out.print("Please type the product barcode: ");
			barcode = scanner.nextLong();
			for(Product p : products) {
				if(p.getBarcode() == barcode) {
					productsWithSameBarcode.add(p);
					contains = true;
				}
			}
			if(!contains)
				System.out.println("There's no product with that code!");
		} while(!contains);
		
		int size = productsWithSameBarcode.size();
		Random random = new Random();
		int selectedProductIndex = random.nextInt(size);
		if(products.contains(productsWithSameBarcode.get(selectedProductIndex))) {
			products.remove(selectedProductIndex);
		}
		else {
			System.out.println("Sale is not completed.");
			return;
		}
		
		((Kid)kid).addPurchasedProduct(productsWithSameBarcode.get(selectedProductIndex));
		
		try {
			updateBeingsFile();
			updateProductsFile();
		} catch(IOException e) {
			e.printStackTrace();
			return;
		}
		
		System.out.println("Sale was successful.");
		
	}
	
	private static void listWinners() {
		int counter = 0;
		for(Being b : beings) {
			if(b instanceof Kid && ((Kid)b).isWinner())
				System.out.println(String.valueOf(++counter) + ") " + b.getName());
		}
		if(counter == 0)
			System.out.println("There's no winner kid.");
	}
	
	private static void prePopulate() {
		try {
		beings.add(new Kid("Danny", "2004-10-15", "Budapest"));
		beings.add(new Kid("Deans", "2002-06-12", "Barcelona"));
		beings.add(new Kid("Joe", "2007-11-23", "Vienna"));
		beings.add(new Kid("Steve", "2003-02-11", "New York"));
		beings.add(new Kid("Jimmy", "2011-01-16", "Istanbul"));
		beings.add(new OompaLoompa("Seri", 64, "strawberry"));
		beings.add(new OompaLoompa("Jeyi", 92, "chocolate"));
		beings.add(new OompaLoompa("Zazu", 83, "oreo"));
		beings.add(new OompaLoompa("Peri", 43, "cherry"));
		beings.add(new OompaLoompa("Lala", 113, "avocado"));
		products.add(new Product("Bread filled in with chocolate", 100, "153421236"));
		products.add(new Product("Bread filled in with chocolate", 100, "223462341", new GoldenTicket("5123", "2018-01-18 15:23")));
		products.add(new Product("Waffle with banana", 101, "623512263"));
		products.add(new Product("Toast with avocado", 102, "333421261"));
		products.add(new Product("Spaghetti with cheese", 103, "823481231", "6591", "2017-05-05 11:34"));
		products.add(new Product("Spaghetti with cheese", 103, "312463273", null)); // using overloaded constructors
		
		updateBeingsFile();
		updateProductsFile();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// Methods that will update the files according to the content of the arraylists that store the beings and the products:
	
	private static void updateBeingsFile() throws IOException {
		PrintWriter writerForBeings = new PrintWriter(new BufferedWriter(new FileWriter(beingsFile)));
		
		for(Being b : beings) {
			writerForBeings.println(b.toString());
		}
		
		writerForBeings.close();
	}
	
	private static void updateProductsFile() throws IOException {
		PrintWriter writerForProducts = new PrintWriter(new BufferedWriter(new FileWriter(productsFile)));

		for(Product p : products) {
			writerForProducts.println(p.toString() + "\n");
		}
		
		writerForProducts.close();
	}

}