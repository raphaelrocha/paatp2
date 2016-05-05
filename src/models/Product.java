package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Product {
	String asin;
	String title;
	HashMap<String,Customer> listCustomer = new HashMap<String,Customer>();
	Group group;
	
	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public ArrayList<Customer> getArrayListCustomer(){
		return new ArrayList<Customer>(listCustomer.values());
	}
	
	public HashMap<String, Customer> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(HashMap<String, Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
