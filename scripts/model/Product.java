package factory.model;

import java.util.Date;
import java.text.ParseException;

public class Product {
	
	private String description;
	private long barcode;
	private String serialNumber;
	private GoldenTicket prizeTicket;
	
	public Product(String description, long barcode, String serialNumber) {
		this.description = description;
		this.barcode = barcode;
		this.serialNumber = serialNumber;
		this.prizeTicket = null;
	}
	
	public Product(String description, long barcode, String serialNumber, GoldenTicket prizeTicket) {
		this.description = description;
		this.barcode = barcode;
		this.serialNumber = serialNumber;
		this.prizeTicket = prizeTicket;
	}
	
	public Product(String description, long barcode, String serialNumber, String code, String raffleDate) throws ParseException {
		this.description = description;
		this.barcode = barcode;
		this.serialNumber = serialNumber;
		this.prizeTicket = new GoldenTicket(code, raffleDate);
	}
	
	public Product(String description, long barcode, String serialNumber, String code, Date raffleDate) {
		this.description = description;
		this.barcode = barcode;
		this.serialNumber = serialNumber;
		this.prizeTicket = new GoldenTicket(code, raffleDate);
	}
	
	public String getDescription() {
		return description;
	}
	
	public long getBarcode() {
		return barcode;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	
	public GoldenTicket getPrizeTicket() {
		return prizeTicket;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setBarcode(long barcode) {
		this.barcode = barcode;
	}
	
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public void setPrizeTicket(GoldenTicket prizeTicket) {
		this.prizeTicket = prizeTicket;
	}
	
	@Override
	public String toString() {
		String stringToReturn = description + ", " + barcode + ", " + serialNumber + ", " + (prizeTicket == null ? "no golden ticket" : prizeTicket);
		
		return stringToReturn;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if(o == null || this.getClass() != o.getClass())
			return false;
		
		Product o2 = (Product) o;
		
		return description.equals(o2.getDescription()) && barcode == o2.getBarcode() && serialNumber.equals(o2.getSerialNumber()) && prizeTicket.equals(o2.getPrizeTicket());
	}
}