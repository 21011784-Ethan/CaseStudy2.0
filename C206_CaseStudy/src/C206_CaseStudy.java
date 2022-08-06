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
	public static boolean adminDeleteStatus;
	public static LocalDate today;

	public static void main(String[] args) {

		C206_CaseStudy cs = new C206_CaseStudy();
		cs.startApp();

	}

	public void startApp() {
		
		today = LocalDate.now();
	
		loginList.add(new UserAccount("Kyle", "admin", "admin@ds.", "pw", "valid", 0));
		loginList.add(new UserAccount("Darren", "user", "user@ds.", "pw", "valid", 0));
		loginList.add(new UserAccount("Johnny", "user", "user2@ds.", "pw", "valid", 0));
		loginList.add(new UserAccount("John", "user", "user3@ds.", "pw", "valid", 0));
		loginList.add(new UserAccount("Seller", "user", "seller@ds.", "pw", "valid", 0));
		loginList.add(new UserAccount("Buyer", "user", "buyer@ds.", "pw", "valid", 0));
		loginList.add(new UserAccount("Amy", "user", "invalid@ds.", "pw", "invalid", 0));
		loginList.add(new UserAccount("Ben", "user", "block@ds.", "pw", "blocked", 0));
		
//		public Bid(String itemName, LocalDate auctionStart, LocalDate endDate, double bidIncrement,String category, int bidId,
//				String sellerEmail, String buyerEmail, double bidPrice)
		
		bidList.add(new Bid("", today, null, 1.0 ,"Electronics",  0, "seller@ds.", null, 100.0, 1));
		
		
//		public Item(String itemName, String description, double minimumBidPrice, LocalDate auctionStart, LocalDate endDate,
//				int bidIncrement, String category)
		
		itemList.add(new Item("Glow Stick", "THE BEST STICK IN THE WORLD!", 10.0, today, null, 1, "Light utilities",1));
		itemList.add(new Item("AK47", "Stand Still for more Accuracy!", 69.0, today, null, 3, "Weapons",2));
		itemList.add(new Item("Hammer", "Build build build", 25.0, today, null, 2, "tools",3));
		itemList.add(new Item("Jett Toy Figure", "WATCH THIS!", 100.0, today, null, 10, "Toys",4));
		itemList.add(new Item("Darren Lee", "I am for sale!!", 10000.0, today, null, 10, "Human",5));
		itemList.add(new Item("Test Item", "I am for sale!!", 100.0, today, null, 10, "Electronics",6));
		itemList.add(new Item("Torch Glow", "Glow test", 10000.0, today, null, 10, "El rado",7));
		
		
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
			if (adminDeleteStatus == true) {
				boolean deleted = adminDeleteUser(loginList);
				if (deleted == true) {
					System.out.println("User has been deleted.");
					adminDeleteStatus = false;
				} else {
					System.out.println("Failed to delete user.");
					adminDeleteStatus = false;
				}
				
				adminDeleteStatus = false;
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
				
				itemMenu(itemList);
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
					adminControls(loginList ,email,password);
					
					
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

				loginList.add(new UserAccount(name, "user", email, password, "valid", 0));
				System.out.println("Successfully registered.");
			} else {
				System.out.println("Failed to register.");
			}
		} else if (emailFound == true) {
			System.out.println("Email already Exists. Please use another Email.");
		}

	}

	public static void userControls(String email, String password) {
		
//		System.out.println("1. View All Biding Items");
//		System.out.println("2. Search Item Name");
//		System.out.println("3. Search Biding Items by Category");
//		System.out.println("4. Bid on Item");
//		System.out.println("5. Sell an Item");
//		System.out.println("6. View my Bids");
//		System.out.println("7. View my Items");
//		System.out.println("8. Change Password");
//		System.out.println("9. Delete Account");
//		System.out.println("10. Logout");
	
		
		int userOption = 0;
		while (userOption != 10) {
			userOption = Helper.readInt("Type User option: ");

			if (userOption == 1) {
				// view all biding items
				viewAllItems(itemList);
				userMenu();
			} else if (userOption == 2) {
				searchItem(itemList);
				userMenu();

			} else if (userOption == 3) {
				searchCategory(itemList);
				userMenu();

			} else if (userOption == 4) {
				//Bid on Item
				bidItem(itemList, currentUser);
				userMenu();
			} else if (userOption == 5) {
				// Add/Sell an Item
				
//				public Item(String itemName, String description, double minimumBidPrice, LocalDate auctionStart, LocalDate endDate,
//						int bidIncrement, String category, int itemId) {
				int lastNumber = 0;
				boolean foundItem = false;
				String itemAdd = Helper.readString("Type item name you want to add: ");
				
				for (int i = 0; i < itemList.size(); i++) {
					if (itemList.get(i).getItemName().equals(itemAdd)) {
						foundItem = true;
					}
					lastNumber = itemList.get(itemList.size()-1).getItemId();
				}
				if (foundItem == false) {
					
					String description = Helper.readString("Type item description: ");
					double minimumBidPrice = Helper.readDouble("Type minimum bid price: ");
					double bidIncrement = Helper.readDouble("Type bid Increment: ");
					LocalDate endDate = null;
					String category = Helper.readString("Type category name for this item: ");
					
					itemList.add(new Item(itemAdd, description, minimumBidPrice, today, endDate, bidIncrement, category, lastNumber++));
					
				}

				
				userMenu();
			} else if (userOption == 6) {
				//View my Bids
				userMenu();
			} else if (userOption == 7) {
				//view My Items

				userMenu();
			} else if (userOption == 8) {
				//Change current password account
				boolean changed = changePassword(currentUser, email,password);
				
				if (changed == true) {
					System.out.println("Password Changed");
					userOption = 10;
				} else {
					System.out.println("Password remains unchanged.");
					userMenu();
				}
	
			} else if (userOption == 9) {
				// Delete Current Account

				String confirmation = Helper.readString("Are you sure you want to DELETE your Account? (Y/N): ");
				if (confirmation.equalsIgnoreCase("y")) {
					userOption = 10;
					deleteStatus = true;
				} else {
					System.out.println("Account has not been deleted.");
				}
			}

		}
		
		logoutStatus = true;
		System.out.println("Successfully Logged out!");

	}
	
	public static void adminControls(ArrayList<UserAccount> loginList, String email, String password) {
		
		int adminOption = 0;
		while (adminOption != 5) {
			adminOption = Helper.readInt("Type an option: ");
			
			if (adminOption == 1) {
				//Manage Items
				manageItems(itemList);
				adminMenu();
			} else if (adminOption == 2) {
				//Manage Categories
				manageCategory(itemList);
				adminMenu();
			} else if (adminOption == 3) {
				//Manage Bids
				
				adminMenu();
			} else if (adminOption == 4) {
				// Manage User Status
				int choice = 0;
				System.out.println("1. Delete users");
				System.out.println("2. Change user Status (BLOCK USERS)");
				System.out.println("3. View all Users");
				choice = Helper.readInt("Please input your choice of action: ");

				if (choice == 1) {				

						adminDeleteStatus = true;
						adminOption = 5;

				} else if (choice == 2) {
					changeUserStatus();
				}else if (choice == 3) {
					for (UserAccount us : loginList) {
						System.out.println();
						System.out.println("Name: " + us.getName());
						System.out.println("Email: " + us.getEmail());
					}
				} else {
					System.out.println("Invalid choice.");
				}
			}
		}
		logoutStatus = true;
		
		if (adminDeleteStatus == false) {
			System.out.println("Successfully Logged out!");
		}

	
	}
	
	public static boolean adminDeleteUser(ArrayList<UserAccount> loginList) {

		boolean deleted = false;
		
		String email = Helper.readString("Type email you want to Delete: ");
		for (int i = 0; i < loginList.size(); i++) {
			UserAccount list = loginList.get(i);
			if (email.equals(list.getEmail()) && list.getRole().equals("user")) {
				loginList.remove(list);
				deleted = true;
			}
		}
		
		return deleted;
	}

	public static void changeUserStatus() {
		boolean found = false;
		String email = Helper.readString("Type email you want to manage: ");
		int status = 0;
		for (int i = 0; i < loginList.size(); i++) {
			if (loginList.get(i).getEmail().equals(email) && !loginList.get(i).getRole().equals("admin")) {
				found = true;
				System.out.println("1. Valid");
				System.out.println("2. Blocked");
				status = Helper.readInt("Select status for this user: ");

				if (status == 1) {
					loginList.get(i).setStatus("valid");
					System.out.println("Changed to valid user");
				} else if (status == 2) {
					loginList.get(i).setStatus("blocked");
					System.out.println("Changed to blocked user");
				} else {
					System.out.println("Invalid Status selection");
				}
			}
		}
		if (found == false) {
			System.out.println("Email not found / Not allowed to be changed");
		}
	}
	
	public static void manageItems(ArrayList<Item> itemList) {
		boolean itemFound = false;
		int id = 0;
		int status = 0;
		String itemName = Helper.readString("Type item name you want to Manage: ");
		
		
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getItemName().equalsIgnoreCase(itemName)) {
				itemFound = true;
				System.out.println("1. Delete item");
				status = Helper.readInt("Type selection: ");
				
				if (status == 1) {
					System.out.println("Item: " + itemList.get(i).getItemName() + " has been removed.");
					itemList.remove(i);
				} else {
					System.out.println("Invalid Selection.");
				}
			}
		
		}

		if (itemFound == false) {
			System.out.println("Item is not found.");
		}
		
	}
	
	public static boolean manageCategory(ArrayList<Item> itemList) {
		boolean categoryFound = false;
		String category = "";
		int option = 0;
		System.out.println("1. Delete an Item");
		System.out.println("2. View All categories");
		option = Helper.readInt("Type the option you want to select: ");

		if (option == 1) {
			category = Helper.readString("Type item Category you want to Delete: ");
			for (int i = 0; i < itemList.size(); i++) {
				if (itemList.get(i).getCategory().equalsIgnoreCase(category)) {
					categoryFound = true;

				}

				if (categoryFound == true) {
					System.out.println("Category: " + itemList.get(i).getCategory() + " has been removed.");
					itemList.remove(i);
				}
			}

		} else if (option == 2) {
			categoryFound = true;
			for (int i = 0; i < itemList.size(); i++) {
				System.out.println(itemList.get(i).getCategory());
			}
		} else {
			System.out.println("Invalid Option");
		}

		if (categoryFound == false) {
			System.out.println("Category is not found.");
		}
		return categoryFound;
	}
	

	public static void deleteUser(ArrayList<UserAccount> currentUser, ArrayList<UserAccount> loginList) {

		for (int z = 0; z < currentUser.size(); z++) {
			String delete = currentUser.get(z).getName();
			for (int i = 0; i < loginList.size(); i++) {
				if (loginList.get(i).getName().equals(delete)) {
					loginList.remove(i);
					currentUser.remove(z);
					System.out.println("Account has been successfully deleted.");
				}

			}

		}
	}
	
	public static void deleteUser2(ArrayList<UserAccount> currentUser) {

		for (int z = 0; z < currentUser.size(); z++) {
			currentUser.remove(z);
		}
	}
	
	public static boolean changePassword(ArrayList<UserAccount> loginList, String email, String password) {
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

		return changed;
		
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
				if (category.strip().equalsIgnoreCase(categoryArray[j].strip()) || category.equalsIgnoreCase(item.getCategory())) {

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
		
		String output = "";
		
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
					output += (("\n" + "Item Name: " + item.getItemName() + "\nDescription: " + item.getDescription()
							+ "\nCategory: " + item.getCategory() + "\nMinimum Bid Price: $" + item.getMinimumBidPrice()
							+ "\nBid Increment: $" + item.getBidIncrement() + "\nAuction Start Date: "
							+ item.getAuctionStart()) + "\n");
					System.out.println();
				}
			}
		}			System.out.println(output);
		
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
	
	public static void bidItem(ArrayList<Item> itemList, ArrayList<UserAccount> currentUser) {
		
		boolean itemFound = false;
		LocalDate endDate = null;
		itemMenu(itemList);
		
		int itemId = Helper.readInt("Type item ID of item you want to BID: ");
		
		for (int z = 0; z < itemList.size(); z++) {
			if (itemList.get(z).getItemId() == itemId) {
				Item item = itemList.get(z);
				
				
				//bidList.add(new Bid(item.getItemName(), today, endDate, item.getBidIncrement(), item.getCategory(),  ))
				
				
				
				
//				public Bid(String itemName, LocalDate auctionStart, LocalDate endDate, double bidIncrement,String category, int bidId,
//				String sellerEmail, String buyerEmail, double bidPrice)
		
				bidList.add(new Bid("", today, null, 1.0 ,"Electronics",  0, "seller@ds.", null, 100.0, 1));
				
				
				
				
			}
		}
		
		if (itemFound == false) {
			System.out.println("Item ID is not found.");
		}
		
//		for (int i = 0; i < itemList.size(); i++) {
//			Item item = itemList.get(i);
//			String[] itemArray = item.getItemName().split(" ");
//
//			for (int j = 0; j < itemArray.length; j++) {
//				if (search.strip().equalsIgnoreCase(itemArray[j].strip()) || search.equalsIgnoreCase(item.getItemName())) {
//
//					System.out.println("Item Name: " + item.getItemName() + "\nDescription: " + item.getDescription()
//							+ "\nCategory: " + item.getCategory() + "\nMinimum Bid Price: $" + item.getMinimumBidPrice()
//							+ "\nBid Increment: $" + item.getBidIncrement() + "\nAuction Start Date: "
//							+ item.getAuctionStart());
//					System.out.println();
//
//				} else {
//					System.out.println("Item not found.");
//				}
//			}
//
//		}
		
		
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
		System.out.println("4. Bid on Item");
		System.out.println("5. Sell an Item");
		System.out.println("6. View my Bids");
		System.out.println("7. View my Items");
		System.out.println("8. Change Password");
		System.out.println("9. Delete Account");
		System.out.println("10. Logout");
	}
	
	public static void adminMenu() {
		Helper.line(70, "-");
		System.out.println("Campus Online Auction Shop (COAS)");
		System.out.println("[YOU ARE AN ADMINISTRATOR]");
		Helper.line(70, "-");
		System.out.println("1. Manage Items (ADD/SELL ALL ITEMS)");
		System.out.println("2. Manage Categories (ADD/DELETE ALL CATEGORIES)");
		System.out.println("3. Manage Bids");
		System.out.println("4. Manage Users (DELETE/BLOCK/VIEW ALL USERS)");
		System.out.println("5. Logout");
	}
	
	public static void itemMenu(ArrayList<Item> itemList) {
		System.out.println("ALL ITEMS: ");
		Helper.line(70, "-");
		for (Item i : itemList) {
			System.out.println(i.getItemName());
		}
		Helper.line(70, "-");
	}
	

}
