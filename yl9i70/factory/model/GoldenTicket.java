package factory.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Random;

public class GoldenTicket {
	
	private String code;
	private Date raffleDate;
	
	private boolean raffled;
	
	@Override
	public String toString() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return code + ": " + dt.format(raffleDate);
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if(o == null || this.getClass() != o.getClass())
			return false;
		
		GoldenTicket o2 = (GoldenTicket) o;
		
		return code.equals(o2.getCode()) && raffleDate.equals(o2.getRaffleDate());
	}
	
	// Constructors
	public GoldenTicket() {
		code = String.valueOf(new Random().nextInt(50000));
		raffleDate = new Date();
	}
	
	public GoldenTicket(String code, Date raffleDate) {
		this.code = code;
		this.raffleDate = raffleDate;
	}
	
	public GoldenTicket(String code, String raffleDate) throws ParseException {
		this.code = code;
		setRaffleDate(raffleDate);
	}
	
	public String getCode() {
		return code;
	}
	
	public Date getRaffleDate() {
		return raffleDate;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setRaffleDate(Date raffleDate) {
		this.raffleDate = raffleDate;
	}
	
	public void setRaffleDate(String raffleDate) throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		this.raffleDate = dt.parse(raffleDate);
	}
	
	public static GoldenTicket createRandom() {
		return new GoldenTicket(String.valueOf(new Random().nextInt(50000)), new Date());
	}
	
	// If the current ticket has been already raffled
	public boolean isRaffled() {
		return (new Date().after(raffleDate));
	}
}