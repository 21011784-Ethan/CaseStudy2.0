import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class C206_CaseStudytest {

	private boolean deleteStatus;
	private boolean logoutStatus;
	private LocalDate today;

	private UserAccount ll1;
	private UserAccount ll2;
	private UserAccount ll3;
	private UserAccount ll4;

////	bidlist
//	private Bid bl1;

	private Item il1;
	private Item il2;
	private Item il3;
	private Item il4;
	private Item il5;
	private Item il6;
	private Item il7;

	ArrayList<UserAccount> currentUser = new ArrayList<UserAccount>();
	ArrayList<UserAccount> loginList = new ArrayList<UserAccount>();
	ArrayList<Bid> bidList = new ArrayList<Bid>();
	ArrayList<Item> itemList = new ArrayList<Item>();

	public C206_CaseStudytest() {
		super();
	}

	void setUp() throws Exception {

		ll1 = new UserAccount("Kyle", "admin", "admin@ds.", "pw", "valid");
		ll2 = new UserAccount("Darren", "user", "user@ds.", "pw", "valid");
		ll3 = new UserAccount("Amy", "user", "invalid@d.", "pw", "invalid");
		ll4 = new UserAccount("Ben", "user", "block@ds.", "pw", "blocked");

//		bidList

		il1 = new Item("Glow Stick", "THE BEST STICK IN THE WORLD!", 10.0, today, null, 1, "Light utilities");
		il2 = new Item("AK47", "Stand Still for more Accuracy!", 69.0, today, null, 3, "Weapons");
		il3 = new Item("Hammer", "Build build build", 25.0, today, null, 2, "tools");
		il4 = new Item("Jett Toy Figure", "WATCH THIS!", 100.0, today, null, 10, "Toys");
		il5 = new Item("Darren Lee", "I am for sale!!", 10000.0, today, null, 10, "Human");
		il6 = new Item("", "I am for sale!!", 10000.0, today, null, 10, "Electronics");
		il7 = new Item("Torch Glow", "Glow test", 10000.0, today, null, 10, "Electronics");
		
		itemList.add(il1);
		itemList.add(il2);
		itemList.add(il3);
		itemList.add(il4);
		itemList.add(il5);
		itemList.add(il6);
		itemList.add(il7);
		

		loginList = new ArrayList<UserAccount>();
		bidList = new ArrayList<Bid>();
		itemList = new ArrayList<Item>();

	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testviewAllItems() {
		// Test if Item list is not null but empty -boundary
		assertNotNull("Test if there is valid Item arraylist to retrieve item", itemList);

		// test if the list of items retrieved from the SourceCentre is empty - boundary
		String allItems = C206_CaseStudy.viewAllItems(itemList);
		String testOutput = "";
		assertEquals("Check that viewAllItems", testOutput, allItems);

//		Given an empty list, after adding 2 items, test if the size of the list is 2 - normal
//		C206_CaseStudy.addItem(itemList, il1);
//		C206_CaseStudy.addItem(itemList, il2);
//		C206_CaseStudy.addItem(itemList, il3);
//		C206_CaseStudy.addItem(itemList, il4);
//		C206_CaseStudy.addItem(itemList, il5);
//		C206_CaseStudy.addItem(itemList, il6);
//		C206_CaseStudy.addItem(itemList, il7);
		
		itemList.add(il1);
		itemList.add(il2);
		itemList.add(il3);
		itemList.add(il4);
		itemList.add(il5);
		itemList.add(il6);
		itemList.add(il7);
		assertEquals("Test that Item arraylist size is 7", 7, itemList.size());

		// test if the expected output string same as the list of items retrieved from the SourceCentre
		allItems = C206_CaseStudy.viewAllItems(itemList);
		for (Item i : itemList) {
			testOutput += ("\nItem Name: " + Item.getItemName() + "\nDescription: " + Item.getDescription() + "\nCategory: "
					+ Item.getCategory() + "\nMinimum Bid Price: $" + Item.getMinimumBidPrice() + "\nBid Increment: $"
					+ Item.getBidIncrement() + "\nAuction Start Date: " + Item.getAuctionStart() + "\n");
		}

//		testOutput = String.format("%-10s %-30s %-10s %-10s %-20s %-20s %-20s\n", "AK47", "Stand Still for more Accuracy!", 69.0, today, null, 3, "Weapons");
//		testOutput += String.format("%-10s %-30s %-10s %-10s %-20s %-20s %-20s\n", "Hammer", "Build build build", 25.0, today, null, 2, "tools");
		assertEquals("Test that viewAllItems", testOutput, allItems);
	}
	
	

	@After
	public void tearDown() throws Exception {
		ll1 = null;
		ll2 = null;
		ll3 = null;
		ll4 = null;

		il1 = null;
		il2 = null;
		il3 = null;
		il4 = null;
		il5 = null;
		il6 = null;
		il7 = null;

		loginList = null;
		bidList = null;
		itemList = null;
	}
}
