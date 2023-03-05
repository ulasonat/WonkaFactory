package factory.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.ParseException;

public class Kid extends Being {
	private Date birthday; // yyyy-MM-dd
	private ArrayList<Product> purchasedProducts;
	private String placeOfBirth;
	
	public Kid(String name, String birthday, String placeOfBirth) throws ParseException {
		super(name);
		purchasedProducts = new ArrayList<>();
		this.placeOfBirth = placeOfBirth;
		setBirthday(birthday);
	}
	
	public Kid(String name, Date birthday, String placeOfBirth) {
		super(name);
		purchasedProducts = new ArrayList<>();
		this.birthday = birthday;
		this.placeOfBirth = placeOfBirth;
	}
	
	public boolean isWinner() {
		for(Product p : purchasedProducts) {
			if(p.getPrizeTicket().isRaffled())
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String stringToReturn = "Kid | " + super.toString() + ", " + dt.format(birthday);
	
		if(!purchasedProducts.isEmpty()) {
			
			stringToReturn += ", products: ";
	
			for(Product p : purchasedProducts) {
				stringToReturn += "; " + p.toString();
			}
		}
		
		return stringToReturn;
		
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if(o == null || this.getClass() != o.getClass())
			return false;
		
		Kid o2 = (Kid) o;
		
		return super.equals(o2) && birthday.equals(o2.getBirthday()) && placeOfBirth.equals(o2.getPlaceOfBirth()) && purchasedProducts.equals(o2.getPurchasedProducts());
	}
	
	public void addPurchasedProduct(Product p) {
		purchasedProducts.add(p);
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public ArrayList<Product> getPurchasedProducts() {
		return purchasedProducts;
	}
	
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public void setBirthday(String birthday) throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		this.birthday = dt.parse(birthday);
	}
	
	public void setPurchasedProducts(ArrayList<Product> purchasedProducts) {
		this.purchasedProducts = purchasedProducts;
	}
	
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
}