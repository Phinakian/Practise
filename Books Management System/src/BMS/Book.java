package BMS;

public class Book{
	private String name;
	private String author;
	private String publishinghouse;
	private String date;
	private String number;
	private float price;
	public Book() {
		this.name = null;
		this.author = null;
		this.publishinghouse = null;
		this.date = null;
		this.number = null;
		this.price = 0.0f;
	}
	public Book(String name, String author, String publishinghouse, float price, String number, String date) {
		this.name = name;
		this.author = author;
		this.publishinghouse = publishinghouse;
		this.date = date;
		this.number = number;
		this.price = price;
	}
	@Override
	public String toString() {
		return name+"\t"+author+"\t"+publishinghouse+"\t"+price+"\t"+number+"\t"+date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublishinghouse() {
		return publishinghouse;
	}
	public void setPublishinghouse(String publishinghouse) {
		this.publishinghouse = publishinghouse;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}	
	
}
