package factory.model;

public class OompaLoompa extends Being {
	private int height;
	private String favoriteFood;
	
	public OompaLoompa(String name, int height, String favoriteFood) {
		super(name);
		this.height = height;
		this.favoriteFood = favoriteFood;
	}
	
	@Override
	public String toString() {
		return "Oompa Loompa | " + super.toString() + ", " + String.valueOf(height) + ", " + favoriteFood;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if(o == null || this.getClass() != o.getClass())
			return false;
		
		OompaLoompa o2 = (OompaLoompa) o;
		
		return super.equals(o2) && height == o2.getHeight() && favoriteFood.equals(o2.getFavoriteFood());
	}
	
	public int getHeight() {
		return height;
	}
	
	public String getFavoriteFood() {
		return favoriteFood;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setFavoriteFood(String favoriteFood) {
		this.favoriteFood = favoriteFood;
	}
}