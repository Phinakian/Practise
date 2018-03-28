package BMS;

import java.util.TreeSet;
import java.util.Iterator;

public class User implements Comparable<User>{
	protected String id;
	protected String password;
	public User(String id,String password) {
		this.id = id;
		this.password = password;
	}
	public void displayBooks(TreeSet<Book> books) {
		Iterator<Book> iterator = books.iterator();
		while (iterator.hasNext())
			System.out.println(iterator.next());
	}
	public void displayUsers(TreeSet<User> users) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext())
			System.out.println(iterator.next());
	}
	public void addBook(Book book,TreeSet<Book> books) {
		if(!books.add(book))
			System.out.println("添加图书失败！");
	}
	
	public Book getBook(String num,TreeSet<Book> books) {
		Iterator<Book> iterator = books.iterator();
		Book book;
		while(iterator.hasNext()) {
			book=iterator.next();
			if(book.getNumber().equals(num))
				return book;
		}
		return null;
	}
	public Book removeBook(Book book,TreeSet<Book> books) {
		if (books.remove(book)) {
			return book;
		} else {
			return null;
		}
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		return Integer.parseInt(id);
	}
	@Override
	public boolean equals(Object obj) {
		User user = (User)obj;
		return this.id.equals(user.getId());
	}
	@Override
	public int compareTo(User o) {
		return this.id.compareTo(o.id);
	}
	@Override
	public String toString() {
		return "用户" + this.id;
	}
}
