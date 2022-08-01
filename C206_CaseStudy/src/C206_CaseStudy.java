import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class C206_CaseStudy {

	public static ArrayList<UserAccount> currentUser = new ArrayList<UserAccount>();
	public static ArrayList<UserAccount> loginList = new ArrayList<UserAccount>();
	public static ArrayList<Bid> bidList = new ArrayList<Bid>();
	public static ArrayList<Item> itemList = new ArrayList<Item>();
	
	public static boolean deleteStatus;
	public static boolean logoutStatus;
	public static LocalDate today;

	public static void main(String[] args) {

		C206_CaseStudy cs = new C206_CaseStudy();
		cs.startApp();

	}

	public void startApp() {
		
		today = LocalDate.now();
	
		loginList.add(new UserAccount("Kyle", "admin", "admin@ds.", "pw", "valid"));
		loginList.add(new UserAccount("Darren", "user", "user@ds.", "pw", "valid"));
		loginList.add(new UserAccount("Johnny", "user", "user2@ds.", "pw", "valid"));
		loginList.add(new UserAccount("John", "user", "user3@ds.", "pw", "valid"));
		loginList.add(new UserAccount("Amy", "user", "invalid@ds.", "pw", "invalid"));
		loginList.add(new UserAccount("Ben", "user", "block@ds.", "pw", "blocked"));
		
//		public Bid(String itemName, LocalDate auctionStart, LocalDate endDate, String category, int bidId,
//				String sellerEmail, String buyerEmail, double bidPrice)
		

		
		
//		public Item(String itemName, String description, double minimumBidPrice, LocalDate auctionStart, LocalDate endDate,
//				int bidIncrement, String category)
		
		itemList.add(new Item("Glow Stick", "THE BEST STICK IN THE WORLD!", 10.0, today, null, 1, "Light utilities"));
		itemList.add(new Item("AK47", "Stand Still for more Accuracy!", 69.0, today, null, 3, "Weapons"));
		itemList.add(new Item("Hammer", "Build build build", 25.0, today, null, 2, "tools"));
		itemList.add(new Item("Jett Toy Figure", "WATCH THIS!", 100.0, today, null, 10, "Toys"));
		itemList.add(new Item("Darren Lee", "I am for sale!!", 10000.0, today, null, 10, "Human"));
		itemList.add(new Item("", "I am for sale!!", 10000.0, today, null, 10, "Electronics"));
		itemList.add(new Item("Torch Glow", "Glow test", 10000.0, today, null, 10, "Electronics"));
		
		
		AppControls();
	}
	
	public static void AppControls() {

		int option = 0;

		while (option != 6) {

			// boolean checks
			if (deleteStatus == true) {
				deleteUser(loginList, currentUser);
				deleteStatus = false;
			}
			if (logoutStatus == true) {
				deleteUser2(currentUser);
				logoutStatus = false;
			}

			// start main program

			menu();
			Helper.line(70, "-");

			option = Helper.readInt("Please Enter Option: ");

			if (option == 1) {
				viewAllItems(itemList);
				
			} else if (option == 2) {
				searchItem(itemList);
				
			} else if (option == 3) {
				searchCategory(itemList);
				
			} else if (option == 4) {
				login(null, null, null);

			} else if (option == 5) {
				register(loginList, null);
			} else if (option == 69) {
				System.out.println(currentUser.size());
				System.out.println(loginList.size());
			}
		}
		System.out.println("Thank you for using our program.");
	}

	public static String loginChecker(ArrayList<UserAccount> loginList, ArrayList<UserAccount> currentUser, String email, String password, String status, String role) {

		status = "invalid";

		for (UserAccount us : loginList) {
			if (email.equals(us.getEmail()) && password.equals(us.getPassword()) && (us.getStatus().equals("valid"))) {
				status = "valid";
				
				if (us.getRole().equals("user")) {
					System.out.println("Account Found... Login Success!");
					Helper.line(70, "-");
					System.out.println("Welcome " + us.getName() + ",");
					
					if (currentUser.contains(us) == false) {
						currentUser.add(us);
					}
										
					userMenu();
					userControls(email,password);
				} else if (us.getRole().equals("admin")) {
					System.out.println("Account Found... Login Success!");
					Helper.line(70, "-");
					System.out.println("Welcome " + us.getName() + ",");
					adminMenu();
					adminControls(0);
				}
				
			} else if (email.equals(us.getEmail()) && password.equals(us.getPassword()) && (us.getStatus().equals("blocked"))) {
				status = "blocked";
			} else if (email.equals(us.getEmail()) && password.equals(us.getPassword()) && (us.getStatus().equals("invalid"))) {
				status = "invalid";
			}
		}
		return status;
	}
	
	public static void login(String email, String status, String role) {
		
		//LOGIN
		email = Helper.readString("Type Email: ");
		status = null;
		role = null;

		if (email.contains("@") && email.contains(".")) {
			String password = Helper.readString("Type Password: ");
			String loginChecker = loginChecker(loginList, currentUser, email, password, status, role);
			

			if (loginChecker.equals("invalid")) {
				System.out.println("Invalid Details. Please try again.");
			} else if (loginChecker.equals("blocked")) {
				System.out.println("This Account is Blocked. Please contact ADMIN.");
			} 
		} else {
			System.out.println("Please type a valid email.");
		}
	}
	
	public static void register(ArrayList<UserAccount> loginList ,String email) {

		email = Helper.readString("Type Email to Register: ");
		boolean emailFound = false;
		
		for (UserAccount us : loginList) {
			if (us.getEmail().equals(email)) {
				emailFound = true;
			} 
		}
		
		if (emailFound == false) {
			if (email.contains("@") && email.contains(".")) {

				String password = Helper.readString("Please type a valid password: ");
				String name = Helper.readString("Please type your name: ");

				loginList.add(new UserAccount(name, "user", email, password, "valid"));
				System.out.println("Successfully registered.");
			} else {
				System.out.println("Failed to register.");
			}
		} else if (emailFound == true) {
			System.out.println("Email already Exists. Please use another Email.");
		}
	

	}

	public static void userControls(String email, String password) {
		
//		Helper.line(70, "-");
//		System.out.println("Campus Online Auction Shop (COAS)");
//		System.out.println("[YOU ARE A VALID USER]");
//		Helper.line(70, "-");
//		System.out.println("1. View All Biding Items");
//		System.out.println("2. Search Item Name");
//		System.out.println("3. Search Biding Items by Category");
//		System.out.println("4. View my Bids");
//		System.out.println("5. View my Items");
//		System.out.println("6. Sell an Item");
//		System.out.println("7. Change Password");
//		System.out.println("8. Delete Account");
//		System.out.println("9. Logout");
		
		int userOption = 0;
		while (userOption != 9) {
			userOption = Helper.readInt("Type User option: ");

			if (userOption == 1) {
				// view all biding items
				viewAllItems(itemList);
			} else if (userOption == 2) {
				searchItem(itemList);

			} else if (userOption == 3) {
				searchCategory(itemList);

			} else if (userOption == 4) {
				

			} else if (userOption == 5) {

			} else if (userOption == 6) {

			} else if (userOption == 7) {
				//Change current password account
				changePassword(currentUser, email,password);
				userOption = 9;
				
			} else if (userOption == 8) {
				// Delete Current Account

				String confirmation = Helper.readString("Are you sure you want to DELETE your Account? (Y/N): ");

				if (confirmation.equalsIgnoreCase("y")) {
					userOption = 9;
					deleteStatus = true;
				} else {
					System.out.println("Account has not been deleted.");
				}
			}

		}
		
		logoutStatus = true;
		System.out.println("Successfully Logged out!");

	}
	
	public static void deleteUser(ArrayList<UserAccount> currentUser, ArrayList<UserAccount> loginList) {

		for (int z = 0; z < currentUser.size(); z++) {
			String delete = currentUser.get(z).getName();
			for (int i = 0; i < loginList.size(); i++) {
				if (loginList.get(i).getName().equals(delete)) {
					loginList.remove(i);
					currentUser.remove(z);
					System.out.println("Account has been successfully deleted.");
					System.out.println(currentUser.size());
					System.out.println(loginList.size());
				}

			}

		}
	}
	
	public static void deleteUser2(ArrayList<UserAccount> currentUser) {

		for (int z = 0; z < currentUser.size(); z++) {
			currentUser.remove(z);
		}
	}

	
	public static void changePassword(ArrayList<UserAccount> loginList, String email, String password) {
		boolean changed = false;
		String newPassword = "";
		password = Helper.readString("Please input your current password: ");
		
		for (int i = 0; i < loginList.size(); i++) {
			
			if (loginList.get(i).getEmail().equals(email) && loginList.get(i).getPassword().equals(password)) {
				newPassword = Helper.readString("Please input your NEW Password: ");
				loginList.get(i).setPassword(newPassword);
				changed = true;
			} 
		}
		if (changed == true) {
			System.out.println("Password Changed");
		} else {
			System.out.println("Password remains unchanged.");
		}
		
	}

	
	public static void adminControls(int option) {
		option = 0;
		while (option != 8) {
			option = Helper.readInt("Type an option: ");
		}

	}
	
	public static void searchCategory(ArrayList<Item> itemList) {
		// Search biding items by category

		String category = Helper.readString("Search category: ");
		
		Helper.line(70, "-");
		System.out.println("ALL ITEMS");
		System.out.println("SEARCHING FOR CATEGORY: " + category);
		Helper.line(70, "-");
		category.toLowerCase();
		System.out.println();
		for (int i = 0; i < itemList.size(); i++) {
			Item item = itemList.get(i);

			String[] categoryArray = item.getCategory().split(" ");
			// System.out.println(Arrays.toString(categoryArray));

			for (int j = 0; j < categoryArray.length; j++) {
				if (category.strip().equalsIgnoreCase(categoryArray[j].strip())) {

					System.out.println("Item Name: " + item.getItemName() + "\nDescription: " + item.getDescription()
							+ "\nCategory: " + item.getCategory() + "\nMinimum Bid Price: $" + item.getMinimumBidPrice()
							+ "\nBid Increment: $" + item.getBidIncrement() + "\nAuction Start Date: "
							+ item.getAuctionStart());

					System.out.println();

				}
			}

		}
	}
	
	public static void searchItem(ArrayList<Item> itemList) {

		String search = Helper.readString("Search Item Name: ");
		
		Helper.line(70, "-");
		System.out.println("ALL ITEMS");
		System.out.println("SEARCHING FOR ITEM: " + search);
		Helper.line(70, "-");
		
		for (int i = 0; i < itemList.size(); i++) {
			Item item = itemList.get(i);

			String[] itemArray = item.getItemName().split(" ");
			//System.out.println(Arrays.toString(itemArray));

			for (int j = 0; j < itemArray.length; j++) {
				if (search.strip().equalsIgnoreCase(itemArray[j].strip())) {

					System.out.println("Item Name: " + item.getItemName() + "\nDescription: " + item.getDescription()
							+ "\nCategory: " + item.getCategory() + "\nMinimum Bid Price: $" + item.getMinimumBidPrice()
							+ "\nBid Increment: $" + item.getBidIncrement() + "\nAuction Start Date: "
							+ item.getAuctionStart());

					System.out.println();

				}
			}

		}
		
	}
	
	public static void viewAllItems(ArrayList<Item> itemList) {
		// view all biding items
		Helper.line(70, "-");
		System.out.println("ALL ITEMS");
		Helper.line(70, "-");
		for (Item i : itemList) {
			System.out.println();
			System.out.println("Item Name: " + i.getItemName() + "\nDescription: " + i.getDescription() + "\nCategory: "
					+ i.getCategory() + "\nMinimum Bid Price: $" + i.getMinimumBidPrice() + "\nBid Increment: $"
					+ i.getBidIncrement() + "\nAuction Start Date: " + i.getAuctionStart());

			System.out.println();
		}

	}

	public static void menu() {
		Helper.line(70, "=");
		System.out.println("Campus Online Auction Shop (COAS)");
		Helper.line(70, "=");
		System.out.println("1. View all Biding Items");
		System.out.println("2. Search Item Name");
		System.out.println("3. Search Biding Items by Category");
		System.out.println("4. Login");
		System.out.println("5. Register");
		System.out.println("6. Exit");

	}
	
	public static void userMenu() {
		Helper.line(70, "-");
		System.out.println("Campus Online Auction Shop (COAS)");
		System.out.println("[YOU ARE A VALID USER]");
		Helper.line(70, "-");
		System.out.println("1. View All Biding Items");
		System.out.println("2. Search Item Name");
		System.out.println("3. Search Biding Items by Category");
		System.out.println("4. View my Bids");
		System.out.println("5. View my Items");
		System.out.println("6. Sell an Item");
		System.out.println("7. Change Password");
		System.out.println("8. Delete Account");
		System.out.println("9. Logout");
	}
	
	public static void adminMenu() {
		Helper.line(70, "-");
		System.out.println("Campus Online Auction Shop (COAS)");
		System.out.println("[YOU ARE AN ADMINISTRATOR]");
		Helper.line(70, "-");
		System.out.println("1. View All Biding Items");
		System.out.println("2. Search Biding Items by Category");
		System.out.println("3. Manage Bids");
		System.out.println("4. View my Items");
		System.out.println("5. View Transaction History");
		System.out.println("6. Change Password");
		System.out.println("7. Delete Account");
		System.out.println("8. Logout");
	}
	

}
