import java.time.LocalDate;

public class Item{

	private String itemName;
	private String description;
	private double minimumBidPrice;
	private LocalDate auctionStart;
	private LocalDate endDate;
	private double bidIncrement;
	private String category;
	private int itemId;
	
	public Item(String itemName,  String description, double minimumBidPrice,  LocalDate auctionStart, LocalDate endDate,
			double bidIncrement2, String category, int itemId) {
		this.itemName = itemName;
		this.description = description;
		this.minimumBidPrice = minimumBidPrice;
		this.auctionStart = auctionStart;
		this.endDate = endDate;
		this.bidIncrement = bidIncrement2;
		this.category = category;
		this.itemId = itemId;
	}
	
	public Item(String itemName, LocalDate auctionStart, LocalDate endDate,double bidIncrement, String category, int itemId) {
		this.itemName = itemName;
		this.description = "";
		this.minimumBidPrice = 1.0;
		this.auctionStart = auctionStart;
		this.endDate = endDate;
		this.bidIncrement = bidIncrement;
		this.category = category;
		this.itemId = itemId;
	}
	


	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getMinimumBidPrice() {
		return minimumBidPrice;
	}

	public void setMinimumBidPrice(double minimumBidPrice) {
		this.minimumBidPrice = minimumBidPrice;
	}

	public LocalDate getAuctionStart() {
		return auctionStart;
	}

	public void setAuctionStart(LocalDate auctionStart) {
		this.auctionStart = auctionStart;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public double getBidIncrement() {
		return bidIncrement;
	}

	public void setBidIncrement(double bidIncrement) {
		this.bidIncrement = bidIncrement;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
}
