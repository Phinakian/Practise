package BMS;

import java.util.TreeSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;


public class BMSSystem {
	private static TreeSet<Book> books;
	private static TreeSet<User> users;
	private static User currentuser = null;
	private static String scanbuffer;
	

	public static void main(String[] args) {
		books = new TreeSet<Book>(new Comparator<Book>(){
			@Override
			public int compare(Book o1, Book o2) {
				return o1.getNumber().compareTo(o2.getNumber());
			}
		});
		books.add(new Book("JavaWeb入门", "老赵", "清华出版社", 89f,"1234560","2010-10-01"));
		books.add(new Book("JavaEE项目实战", "老汤", "机械工业出版社", 189f,"1234561","2010-10-02"));
		books.add(new Book("Java基础入门", "老钟", "北大出版社", 50f,"1234562","2010-10-03"));
		books.add(new Book("HTML入门", "老张", "邮电出版社", 85f,"1234563","2000-10-04"));
		books.add(new Book("XML入门", "老张", "电力出版社", 75f,"1234564","2010-10-05"));
		books.add(new Book("Android入门", "老蔡", "传智出版社", 100f,"1234565","1999-10-06"));
		users = new TreeSet<User>();
		users.add(new Administrator("administrator", "administrator"));
		users.add(new User("aaa", "aaaaaaaa"));
		Scanner scan = new Scanner(System.in);
		while (currentuser == null) {
			System.out.println("******************************************");
			System.out.println("*                                        *");
			System.out.println("*                                        *");
			System.out.println("*                                        *");
			System.out.println("*                                        *");
			System.out.println("*          欢迎使用图书管理系统                    *");
			System.out.println("*                                        *");
			System.out.println("*                                        *");
			System.out.println("*                                        *");
			System.out.println("*                                        *");
			System.out.println("*                                        *");
			System.out.println("******************************************");
			System.out.println("请选择功能：用户登入(A) 注册用户(B) 退出系统(Q)");
			switch (scan.next().toUpperCase()) {
			case "A": {
				System.out.println("请输入用户名：");
				scanbuffer = scan.next();
				System.out.println("请输入密码：");
				while (!login(scanbuffer, scan.next())) {
					System.out.println("请输入用户名：");
					scanbuffer = scan.next();
					System.out.println("请输入密码：");
				}
				break;
			}
			case "B": {
				System.out.println("请输入用户名：");
				scanbuffer = scan.next();
				System.out.println("请输入密码：");
				while (!register(scanbuffer, scan.next())) {
					System.out.println("请输入用户名：");
					scanbuffer = scan.next();
					System.out.println("请输入密码：");
				}
				break;
			}
			case "Q": {
				System.exit(0);
				break;
			}
			default: {
				System.out.println("您的输入有误，请重新输入");
			}
			}
			while (currentuser != null) {
				System.out.println("欢迎" + currentuser);
				System.out.println("请选择功能：查看图书(A) 添加图书(B) 修改图书(C) 删除图书(D) 修改用户名(E) 修改密码(F) 查看所有用户(I) 注销(Q)");
				switch (scan.next().toUpperCase()) {
				case "A": {
					System.out.println("|书名|\t|作者|\t|出版社|\t|价格|\t|书号|\t|出版日期|");
					currentuser.displayBooks(books);
					System.out.println("请选择查看方式：(A)价格排序 (B)作者排序 (C)出版日期排序 (E)返回上一级");
					scanbuffer = scan.next();
					while (!scanbuffer.equalsIgnoreCase("E")) {
						switch (scanbuffer.toUpperCase()) {
						case "A": {
							System.out.println("|书名|\t|作者|\t|出版社|\t|价格|\t|书号|\t|出版日期|");
							TreeSet<Book> temp = books;
							books = new TreeSet<Book>(new Comparator<Book>(){
								@Override
								public int compare(Book o1, Book o2) {
									return (int) (o1.getPrice() - o2.getPrice());
								}
							});
							books.addAll(temp);
							currentuser.displayBooks(books);
							System.out.println("请选择查看方式：(A)价格排序 (B)作者排序 (C)出版日期排序 (E)返回上一级");
							scanbuffer = scan.next();
							break;
						}
						case "B": {
							System.out.println("|书名|\t|作者|\t|出版社|\t|价格|\t|书号|\t|出版日期|");
							TreeSet<Book> temp = books;
							books = new TreeSet<Book>(new Comparator<Book>(){
								@Override
								public int compare(Book o1, Book o2) {
									return o1.getAuthor().compareTo(o2.getAuthor());
								}
							});
							books.addAll(temp);
							currentuser.displayBooks(books);
							System.out.println("请选择查看方式：(A)价格排序 (B)作者排序 (C)出版日期排序 (E)返回上一级");
							scanbuffer = scan.next();
							break;
						}
						case "C": {
							System.out.println("|书名|\t|作者|\t|出版社|\t|价格|\t|书号|\t|出版日期|");
							TreeSet<Book> temp = books;
							books = new TreeSet<Book>(new Comparator<Book>(){
								@Override
								public int compare(Book o1, Book o2) {
									return o1.getDate().compareTo(o2.getDate());
								}
							});
							books.addAll(temp);
							currentuser.displayBooks(books);
							System.out.println("请选择查看方式：(A)价格排序 (B)作者排序 (C)出版日期排序 (E)返回上一级");
							scanbuffer = scan.next();
							break;
						}
						case "E": {
							break;
						}
						default: {
							System.out.println("您的输入有误，请重新输入");
							scanbuffer = scan.next();
						}
						}
					}
					break;
				}
				case "B": {
					Book book = new Book();
					System.out.println("增加图书");
					System.out.println("请输入书名：");
					scanbuffer = scan.next();
					book.setName(scanbuffer);
					System.out.println("请输入作者：");
					scanbuffer = scan.next();
					book.setAuthor(scanbuffer);
					System.out.println("请输入出版社：");
					scanbuffer = scan.next();
					book.setPublishinghouse(scanbuffer);
					System.out.println("请输入价格：");
					book.setPrice(scan.nextFloat());
					System.out.println("请输入书号：");
					scanbuffer = scan.next();
					if(scanbuffer.matches("\\d+")) {
						book.setNumber(scanbuffer);
					} else {
						System.out.println("非法的书号");
						break;
					}
					System.out.println("请输入出版日期：");
					scanbuffer = scan.next();
					if(scanbuffer.matches("\\d{4}-\\d{2}-\\d{2}")) {
						book.setDate(scanbuffer);
					} else {
						System.out.println("非法的日期格式");
						break;
					}
					System.out.println("新书信息如下：书名：" + book.getName() + " 作者：" + book.getAuthor() + " 出版社：" + book.getPublishinghouse() + " 价格：" + book.getPrice() + " 书号：" + book.getNumber() + " 出版日期：" + book.getDate());
					System.out.println("是否保存该书：(Y)是 (N)否");
					switch (scan.next().toUpperCase()) {
					case "Y": {
						System.out.println("正在添加");
						if(books.add(book)){
							System.out.println("添加成功！");
						} else {
							System.out.println("添加失败！");
						}
						break;
					}
					case "N": {
						break;
					}
					default: {
						System.out.println("您的输入有误，请重新输入");
					}
					}
					break;
				}
				case "C": {
					Book book;
					System.out.println("请输入要修改的书号：");
					scanbuffer = scan.next();
					if(scanbuffer.matches("\\d+")) {
						book = currentuser.getBook(scanbuffer, books);
						System.out.println("|书名|\t|作者|\t|出版社|\t|价格|\t|书号|\t|出版日期|");
						System.out.println(book);
						System.out.println("请输入要修改的属性：书名修改(A) 作者修改(B) 出版社修改(C) 价格修改(D) 书号修改(E) 出版日期修改(F) 返回上一级(Q)");
						scanbuffer = scan.next();
						while (!scanbuffer.equalsIgnoreCase("Q")) {
							switch (scanbuffer.toUpperCase()) {
							case "A": {
								System.out.println("请输入书名");
								scanbuffer = scan.next();
								book.setName(scanbuffer);
								System.out.println("|书名|\t|作者|\t|出版社|\t|价格|\t|书号|\t|出版日期|");
								System.out.println(book);
								System.out.println("请输入要修改的属性：书名修改(A) 作者修改(B) 出版社修改(C) 价格修改(D) 书号修改(E) 出版日期修改(F) 返回上一级(Q)");
								scanbuffer = scan.next();
								break;
							}
							case "B": {
								System.out.println("请输入作者");
								scanbuffer = scan.next();
								book.setAuthor(scanbuffer);
								System.out.println("|书名|\t|作者|\t|出版社|\t|价格|\t|书号|\t|出版日期|");
								System.out.println(book);
								System.out.println("请输入要修改的属性：书名修改(A) 作者修改(B) 出版社修改(C) 价格修改(D) 书号修改(E) 出版日期修改(F) 返回上一级(Q)");
								scanbuffer = scan.next();
								break;
							}
							case "C": {
								System.out.println("请输入出版社");
								scanbuffer = scan.next();
								book.setPublishinghouse(scanbuffer);
								System.out.println("|书名|\t|作者|\t|出版社|\t|价格|\t|书号|\t|出版日期|");
								System.out.println(book);
								System.out.println("请输入要修改的属性：书名修改(A) 作者修改(B) 出版社修改(C) 价格修改(D) 书号修改(E) 出版日期修改(F) 返回上一级(Q)");
								scanbuffer = scan.next();
								break;
							}
							case "D": {
								System.out.println("请输入价格");
								book.setPrice(scan.nextFloat());
								System.out.println("|书名|\t|作者|\t|出版社|\t|价格|\t|书号|\t|出版日期|");
								System.out.println(book);
								System.out.println("请输入要修改的属性：书名修改(A) 作者修改(B) 出版社修改(C) 价格修改(D) 书号修改(E) 出版日期修改(F) 返回上一级(Q)");
								scanbuffer = scan.next();
								break;
							}
							case "E": {
								System.out.println("请输入书号");
								scanbuffer = scan.next();
								if (scanbuffer.matches("\\d+")) {
									book.setNumber(scanbuffer);
								} else {
									System.out.println("非法的书号");
								}
								System.out.println("|书名|\t|作者|\t|出版社|\t|价格|\t|书号|\t|出版日期|");
								System.out.println(book);
								System.out.println("请输入要修改的属性：书名修改(A) 作者修改(B) 出版社修改(C) 价格修改(D) 书号修改(E) 出版日期修改(F) 返回上一级(Q)");
								scanbuffer = scan.next();
								break;
							}
							case "F": {
								System.out.println("请输入出版日期");
								scanbuffer = scan.next();
								if (scanbuffer.matches("\\d{4}-\\d{2}-\\d{2}")) {
									book.setDate(scanbuffer);
								} else {
									System.out.println("非法的日期格式");
								}
								System.out.println("|书名|\t|作者|\t|出版社|\t|价格|\t|书号|\t|出版日期|");
								System.out.println(book);
								System.out.println("请输入要修改的属性：书名修改(A) 作者修改(B) 出版社修改(C) 价格修改(D) 书号修改(E) 出版日期修改(F) 返回上一级(Q)");
								scanbuffer = scan.next();
								break;
							}
							case "Q": {
								break;
							}
							default: {
								System.out.println("您的输入有误，请重新输入");
								scanbuffer = scan.next();
							}
							}
						}
					} else {
						System.out.println("非法的书号");
					}
					break;
				}
				case "D": {
					System.out.println("请输入要删除的操作：按书名删除(A) 按作者删除(B) 按书号(C) 返回上一级(E)");
					TreeSet<Book> ts = new TreeSet<Book>(new Comparator<Book>(){
						@Override
						public int compare(Book o1, Book o2) {
							return o1.getNumber().compareTo(o2.getNumber());
						}
					});
					Iterator<Book> iterator = books.iterator();
					switch (scan.next().toUpperCase()) {
					case "A": {
						System.out.println("请输入书名");
						scanbuffer = scan.next();
						System.out.println("|书名|\t|作者|\t|出版社|\t|价格|\t|书号|\t|出版日期|");
						while(iterator.hasNext()) {
							Book book = iterator.next();
							if(book.getName().equals(scanbuffer)) {
								System.out.println(book);
								ts.add(book);
							}
						}
						if(ts.isEmpty()) {
							System.out.println("未找到该书");
						} else {
							System.out.println("是否确认删除：(Y)是(N)否");
							switch(scan.next().toUpperCase()) {
							case "Y":{
								System.out.println("正在刪除");
								books.removeAll(ts);
								System.out.println("刪除完成");
								break;
							}
							case "N":{
								break;
							}
							default:{
								System.out.println("您的输入有误，请重新输入");
							}
							}
							
						}
						break;
					}
					case "B": {
						System.out.println("请输入作者");
						scanbuffer = scan.next();
						System.out.println("|书名|\t|作者|\t|出版社|\t|价格|\t|书号|\t|出版日期|");
						while(iterator.hasNext()) {
							Book book = iterator.next();
							if(book.getAuthor().equals(scanbuffer)) {
								System.out.println(book);
								ts.add(book);								
							}
						}
						if(ts.isEmpty()) {
							System.out.println("未找到该书");
						} else {
							System.out.println("是否确认删除：(Y)是(N)否");
							switch(scan.next().toUpperCase()) {
							case "Y":{
								System.out.println("正在刪除");
								books.removeAll(ts);
								System.out.println("刪除完成");
								break;
							}
							case "N":{
								break;
							}
							default:{
								System.out.println("您的输入有误，请重新输入");
							}
						}
						break;
					}
					}
					case "C": {
						System.out.println("请输入书号");
						scanbuffer = scan.next();
						System.out.println("|书名|\t|作者|\t|出版社|\t|价格|\t|书号|\t|出版日期|");
						while(iterator.hasNext()) {
							Book book = iterator.next();
							if(book.getNumber().equals(scanbuffer)) {
								System.out.println(book);
								ts.add(book);
							}
						}
						if(ts.isEmpty()) {
							System.out.println("未找到该书");
						} else {
							System.out.println("是否确认删除：(Y)是(N)否");
							switch(scan.next().toUpperCase()) {
							case "Y":{
								System.out.println("正在刪除");
								books.removeAll(ts);
								System.out.println("刪除完成");
								break;
							}
							case "N":{
								break;
							}
							default:{
								System.out.println("您的输入有误，请重新输入");
							}
						}
						break;
					}
					}
					case "E": {
						break;
					}
					default: {
						System.out.println("您的输入有误，请重新输入");
					}
					}
					break;
				}
				case "E": {
					System.out.println("输入新用户名");
					scanbuffer=scan.next();
					if (scanbuffer.matches("[a-zA-Z0-9]+")) {
						if(!users.contains(new User(scanbuffer,"123"))) {
							currentuser.setId(scanbuffer);
						} else {
							System.out.println("已有的用戶名");
						}
					} else {
						System.out.println("非法的用戶名");
					}
					break;
					}
				case "F": {
					System.out.println("输入新密码");
					scanbuffer=scan.next();
					if(scanbuffer.matches("[a-zA-Z0-9]{8,}")) {
						currentuser.setPassword(scanbuffer);
					} else {
						System.out.println("密码不符合要求");
					}
					break;
					}
				case "Q": {
					currentuser = null;
					break;
				}
				default: {
					System.out.println("您的输入有误，请重新输入");
				}
				}
				
			}
		}
	scan.close();
	}

	private static boolean login(String id, String password) {
		Iterator<User> iterator = users.iterator();
		User user;
		while (iterator.hasNext()) {
			user = iterator.next();
			if (user.getId().equals(id)) {
				if (user.getPassword().equals(password)) {
					currentuser = user;
					return true;
				} else {
					System.out.println("密码错误！");
					return false;
				}
			}
		}
		System.out.println("没有找到该用户！");
		return false;
	}

	private static boolean register(String id, String password) {
		if (id.matches("[a-zA-Z0-9]+")) {
			if (password.matches("[a-zA-Z0-9]{8,}")) {
				User user = new User(id, password);
				if (users.add(user)) {
					// currentuser = user;
					return true;
				} else {
					System.out.println("用戶名已被占用");
					return false;
				}
			} else {
				System.out.println("密码不符合要求");
			}
		} else {
			System.out.println("非法的用戶名");
		}
		return false;
	}

}
