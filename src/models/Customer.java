package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer {
	String id;
	HashMap<String,Product> listProduts = new HashMap<String,Product>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HashMap<String, Product> getListProduts() {
		return listProduts;
	}

	public void setListProduts(HashMap<String, Product> listProduts) {
		this.listProduts = listProduts;
	}
	
	public ArrayList<Product> getArrayListProducts(){
		return new ArrayList<Product>(listProduts.values());
	}

	//Ci
	public int getTotalRatingsRegostred(){
		return listProduts.size();
	}
	
	public float getMaxRemaingRatings(){
		return (float) (listProduts.size() * 0.4);
	}
	
}
