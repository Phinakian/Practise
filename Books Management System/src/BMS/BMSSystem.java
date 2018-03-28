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
		books.add(new Book("JavaWeb����", "����", "�廪������", 89f,"1234560","2010-10-01"));
		books.add(new Book("JavaEE��Ŀʵս", "����", "��е��ҵ������", 189f,"1234561","2010-10-02"));
		books.add(new Book("Java��������", "����", "���������", 50f,"1234562","2010-10-03"));
		books.add(new Book("HTML����", "����", "�ʵ������", 85f,"1234563","2000-10-04"));
		books.add(new Book("XML����", "����", "����������", 75f,"1234564","2010-10-05"));
		books.add(new Book("Android����", "�ϲ�", "���ǳ�����", 100f,"1234565","1999-10-06"));
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
			System.out.println("*          ��ӭʹ��ͼ�����ϵͳ                    *");
			System.out.println("*                                        *");
			System.out.println("*                                        *");
			System.out.println("*                                        *");
			System.out.println("*                                        *");
			System.out.println("*                                        *");
			System.out.println("******************************************");
			System.out.println("��ѡ���ܣ��û�����(A) ע���û�(B) �˳�ϵͳ(Q)");
			switch (scan.next().toUpperCase()) {
			case "A": {
				System.out.println("�������û�����");
				scanbuffer = scan.next();
				System.out.println("���������룺");
				while (!login(scanbuffer, scan.next())) {
					System.out.println("�������û�����");
					scanbuffer = scan.next();
					System.out.println("���������룺");
				}
				break;
			}
			case "B": {
				System.out.println("�������û�����");
				scanbuffer = scan.next();
				System.out.println("���������룺");
				while (!register(scanbuffer, scan.next())) {
					System.out.println("�������û�����");
					scanbuffer = scan.next();
					System.out.println("���������룺");
				}
				break;
			}
			case "Q": {
				System.exit(0);
				break;
			}
			default: {
				System.out.println("����������������������");
			}
			}
			while (currentuser != null) {
				System.out.println("��ӭ" + currentuser);
				System.out.println("��ѡ���ܣ��鿴ͼ��(A) ���ͼ��(B) �޸�ͼ��(C) ɾ��ͼ��(D) �޸��û���(E) �޸�����(F) �鿴�����û�(I) ע��(Q)");
				switch (scan.next().toUpperCase()) {
				case "A": {
					System.out.println("|����|\t|����|\t|������|\t|�۸�|\t|���|\t|��������|");
					currentuser.displayBooks(books);
					System.out.println("��ѡ��鿴��ʽ��(A)�۸����� (B)�������� (C)������������ (E)������һ��");
					scanbuffer = scan.next();
					while (!scanbuffer.equalsIgnoreCase("E")) {
						switch (scanbuffer.toUpperCase()) {
						case "A": {
							System.out.println("|����|\t|����|\t|������|\t|�۸�|\t|���|\t|��������|");
							TreeSet<Book> temp = books;
							books = new TreeSet<Book>(new Comparator<Book>(){
								@Override
								public int compare(Book o1, Book o2) {
									return (int) (o1.getPrice() - o2.getPrice());
								}
							});
							books.addAll(temp);
							currentuser.displayBooks(books);
							System.out.println("��ѡ��鿴��ʽ��(A)�۸����� (B)�������� (C)������������ (E)������һ��");
							scanbuffer = scan.next();
							break;
						}
						case "B": {
							System.out.println("|����|\t|����|\t|������|\t|�۸�|\t|���|\t|��������|");
							TreeSet<Book> temp = books;
							books = new TreeSet<Book>(new Comparator<Book>(){
								@Override
								public int compare(Book o1, Book o2) {
									return o1.getAuthor().compareTo(o2.getAuthor());
								}
							});
							books.addAll(temp);
							currentuser.displayBooks(books);
							System.out.println("��ѡ��鿴��ʽ��(A)�۸����� (B)�������� (C)������������ (E)������һ��");
							scanbuffer = scan.next();
							break;
						}
						case "C": {
							System.out.println("|����|\t|����|\t|������|\t|�۸�|\t|���|\t|��������|");
							TreeSet<Book> temp = books;
							books = new TreeSet<Book>(new Comparator<Book>(){
								@Override
								public int compare(Book o1, Book o2) {
									return o1.getDate().compareTo(o2.getDate());
								}
							});
							books.addAll(temp);
							currentuser.displayBooks(books);
							System.out.println("��ѡ��鿴��ʽ��(A)�۸����� (B)�������� (C)������������ (E)������һ��");
							scanbuffer = scan.next();
							break;
						}
						case "E": {
							break;
						}
						default: {
							System.out.println("����������������������");
							scanbuffer = scan.next();
						}
						}
					}
					break;
				}
				case "B": {
					Book book = new Book();
					System.out.println("����ͼ��");
					System.out.println("������������");
					scanbuffer = scan.next();
					book.setName(scanbuffer);
					System.out.println("���������ߣ�");
					scanbuffer = scan.next();
					book.setAuthor(scanbuffer);
					System.out.println("����������磺");
					scanbuffer = scan.next();
					book.setPublishinghouse(scanbuffer);
					System.out.println("������۸�");
					book.setPrice(scan.nextFloat());
					System.out.println("��������ţ�");
					scanbuffer = scan.next();
					if(scanbuffer.matches("\\d+")) {
						book.setNumber(scanbuffer);
					} else {
						System.out.println("�Ƿ������");
						break;
					}
					System.out.println("������������ڣ�");
					scanbuffer = scan.next();
					if(scanbuffer.matches("\\d{4}-\\d{2}-\\d{2}")) {
						book.setDate(scanbuffer);
					} else {
						System.out.println("�Ƿ������ڸ�ʽ");
						break;
					}
					System.out.println("������Ϣ���£�������" + book.getName() + " ���ߣ�" + book.getAuthor() + " �����磺" + book.getPublishinghouse() + " �۸�" + book.getPrice() + " ��ţ�" + book.getNumber() + " �������ڣ�" + book.getDate());
					System.out.println("�Ƿ񱣴���飺(Y)�� (N)��");
					switch (scan.next().toUpperCase()) {
					case "Y": {
						System.out.println("�������");
						if(books.add(book)){
							System.out.println("��ӳɹ���");
						} else {
							System.out.println("���ʧ�ܣ�");
						}
						break;
					}
					case "N": {
						break;
					}
					default: {
						System.out.println("����������������������");
					}
					}
					break;
				}
				case "C": {
					Book book;
					System.out.println("������Ҫ�޸ĵ���ţ�");
					scanbuffer = scan.next();
					if(scanbuffer.matches("\\d+")) {
						book = currentuser.getBook(scanbuffer, books);
						System.out.println("|����|\t|����|\t|������|\t|�۸�|\t|���|\t|��������|");
						System.out.println(book);
						System.out.println("������Ҫ�޸ĵ����ԣ������޸�(A) �����޸�(B) �������޸�(C) �۸��޸�(D) ����޸�(E) ���������޸�(F) ������һ��(Q)");
						scanbuffer = scan.next();
						while (!scanbuffer.equalsIgnoreCase("Q")) {
							switch (scanbuffer.toUpperCase()) {
							case "A": {
								System.out.println("����������");
								scanbuffer = scan.next();
								book.setName(scanbuffer);
								System.out.println("|����|\t|����|\t|������|\t|�۸�|\t|���|\t|��������|");
								System.out.println(book);
								System.out.println("������Ҫ�޸ĵ����ԣ������޸�(A) �����޸�(B) �������޸�(C) �۸��޸�(D) ����޸�(E) ���������޸�(F) ������һ��(Q)");
								scanbuffer = scan.next();
								break;
							}
							case "B": {
								System.out.println("����������");
								scanbuffer = scan.next();
								book.setAuthor(scanbuffer);
								System.out.println("|����|\t|����|\t|������|\t|�۸�|\t|���|\t|��������|");
								System.out.println(book);
								System.out.println("������Ҫ�޸ĵ����ԣ������޸�(A) �����޸�(B) �������޸�(C) �۸��޸�(D) ����޸�(E) ���������޸�(F) ������һ��(Q)");
								scanbuffer = scan.next();
								break;
							}
							case "C": {
								System.out.println("�����������");
								scanbuffer = scan.next();
								book.setPublishinghouse(scanbuffer);
								System.out.println("|����|\t|����|\t|������|\t|�۸�|\t|���|\t|��������|");
								System.out.println(book);
								System.out.println("������Ҫ�޸ĵ����ԣ������޸�(A) �����޸�(B) �������޸�(C) �۸��޸�(D) ����޸�(E) ���������޸�(F) ������һ��(Q)");
								scanbuffer = scan.next();
								break;
							}
							case "D": {
								System.out.println("������۸�");
								book.setPrice(scan.nextFloat());
								System.out.println("|����|\t|����|\t|������|\t|�۸�|\t|���|\t|��������|");
								System.out.println(book);
								System.out.println("������Ҫ�޸ĵ����ԣ������޸�(A) �����޸�(B) �������޸�(C) �۸��޸�(D) ����޸�(E) ���������޸�(F) ������һ��(Q)");
								scanbuffer = scan.next();
								break;
							}
							case "E": {
								System.out.println("���������");
								scanbuffer = scan.next();
								if (scanbuffer.matches("\\d+")) {
									book.setNumber(scanbuffer);
								} else {
									System.out.println("�Ƿ������");
								}
								System.out.println("|����|\t|����|\t|������|\t|�۸�|\t|���|\t|��������|");
								System.out.println(book);
								System.out.println("������Ҫ�޸ĵ����ԣ������޸�(A) �����޸�(B) �������޸�(C) �۸��޸�(D) ����޸�(E) ���������޸�(F) ������һ��(Q)");
								scanbuffer = scan.next();
								break;
							}
							case "F": {
								System.out.println("�������������");
								scanbuffer = scan.next();
								if (scanbuffer.matches("\\d{4}-\\d{2}-\\d{2}")) {
									book.setDate(scanbuffer);
								} else {
									System.out.println("�Ƿ������ڸ�ʽ");
								}
								System.out.println("|����|\t|����|\t|������|\t|�۸�|\t|���|\t|��������|");
								System.out.println(book);
								System.out.println("������Ҫ�޸ĵ����ԣ������޸�(A) �����޸�(B) �������޸�(C) �۸��޸�(D) ����޸�(E) ���������޸�(F) ������һ��(Q)");
								scanbuffer = scan.next();
								break;
							}
							case "Q": {
								break;
							}
							default: {
								System.out.println("����������������������");
								scanbuffer = scan.next();
							}
							}
						}
					} else {
						System.out.println("�Ƿ������");
					}
					break;
				}
				case "D": {
					System.out.println("������Ҫɾ���Ĳ�����������ɾ��(A) ������ɾ��(B) �����(C) ������һ��(E)");
					TreeSet<Book> ts = new TreeSet<Book>(new Comparator<Book>(){
						@Override
						public int compare(Book o1, Book o2) {
							return o1.getNumber().compareTo(o2.getNumber());
						}
					});
					Iterator<Book> iterator = books.iterator();
					switch (scan.next().toUpperCase()) {
					case "A": {
						System.out.println("����������");
						scanbuffer = scan.next();
						System.out.println("|����|\t|����|\t|������|\t|�۸�|\t|���|\t|��������|");
						while(iterator.hasNext()) {
							Book book = iterator.next();
							if(book.getName().equals(scanbuffer)) {
								System.out.println(book);
								ts.add(book);
							}
						}
						if(ts.isEmpty()) {
							System.out.println("δ�ҵ�����");
						} else {
							System.out.println("�Ƿ�ȷ��ɾ����(Y)��(N)��");
							switch(scan.next().toUpperCase()) {
							case "Y":{
								System.out.println("���ڄh��");
								books.removeAll(ts);
								System.out.println("�h�����");
								break;
							}
							case "N":{
								break;
							}
							default:{
								System.out.println("����������������������");
							}
							}
							
						}
						break;
					}
					case "B": {
						System.out.println("����������");
						scanbuffer = scan.next();
						System.out.println("|����|\t|����|\t|������|\t|�۸�|\t|���|\t|��������|");
						while(iterator.hasNext()) {
							Book book = iterator.next();
							if(book.getAuthor().equals(scanbuffer)) {
								System.out.println(book);
								ts.add(book);								
							}
						}
						if(ts.isEmpty()) {
							System.out.println("δ�ҵ�����");
						} else {
							System.out.println("�Ƿ�ȷ��ɾ����(Y)��(N)��");
							switch(scan.next().toUpperCase()) {
							case "Y":{
								System.out.println("���ڄh��");
								books.removeAll(ts);
								System.out.println("�h�����");
								break;
							}
							case "N":{
								break;
							}
							default:{
								System.out.println("����������������������");
							}
						}
						break;
					}
					}
					case "C": {
						System.out.println("���������");
						scanbuffer = scan.next();
						System.out.println("|����|\t|����|\t|������|\t|�۸�|\t|���|\t|��������|");
						while(iterator.hasNext()) {
							Book book = iterator.next();
							if(book.getNumber().equals(scanbuffer)) {
								System.out.println(book);
								ts.add(book);
							}
						}
						if(ts.isEmpty()) {
							System.out.println("δ�ҵ�����");
						} else {
							System.out.println("�Ƿ�ȷ��ɾ����(Y)��(N)��");
							switch(scan.next().toUpperCase()) {
							case "Y":{
								System.out.println("���ڄh��");
								books.removeAll(ts);
								System.out.println("�h�����");
								break;
							}
							case "N":{
								break;
							}
							default:{
								System.out.println("����������������������");
							}
						}
						break;
					}
					}
					case "E": {
						break;
					}
					default: {
						System.out.println("����������������������");
					}
					}
					break;
				}
				case "E": {
					System.out.println("�������û���");
					scanbuffer=scan.next();
					if (scanbuffer.matches("[a-zA-Z0-9]+")) {
						if(!users.contains(new User(scanbuffer,"123"))) {
							currentuser.setId(scanbuffer);
						} else {
							System.out.println("���е��Ñ���");
						}
					} else {
						System.out.println("�Ƿ����Ñ���");
					}
					break;
					}
				case "F": {
					System.out.println("����������");
					scanbuffer=scan.next();
					if(scanbuffer.matches("[a-zA-Z0-9]{8,}")) {
						currentuser.setPassword(scanbuffer);
					} else {
						System.out.println("���벻����Ҫ��");
					}
					break;
					}
				case "Q": {
					currentuser = null;
					break;
				}
				default: {
					System.out.println("����������������������");
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
					System.out.println("�������");
					return false;
				}
			}
		}
		System.out.println("û���ҵ����û���");
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
					System.out.println("�Ñ����ѱ�ռ��");
					return false;
				}
			} else {
				System.out.println("���벻����Ҫ��");
			}
		} else {
			System.out.println("�Ƿ����Ñ���");
		}
		return false;
	}

}
