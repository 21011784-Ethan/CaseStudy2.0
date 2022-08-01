import java.time.LocalDate;

public class Bid extends Item {

	private int bidId;
	private String sellerEmail;
	private String buyerEmail;
	private double bidPrice;

	public Bid(String itemName, LocalDate auctionStart, LocalDate endDate, String category, int bidId,
			String sellerEmail, String buyerEmail, double bidPrice) {
		super(itemName, auctionStart, endDate, category);
		this.bidId = bidId;
		this.sellerEmail = sellerEmail;
		this.buyerEmail = buyerEmail;
		this.bidPrice = bidPrice;
	}

	public int getBidId() {
		return bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {

		this.sellerEmail = sellerEmail;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}


}
