package com.example.flipkart;

import com.google.gson.annotations.SerializedName;

public class ResponseProductItem{

	@SerializedName("image")
	private String image;

	@SerializedName("price")
	private Object price;

	@SerializedName("rating")
	private Rating rating;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("category")
	private String category;

	public String getImage(){
		return image;
	}

	public Object getPrice(){
		return price;
	}

	public Rating getRating(){
		return rating;
	}

	public String getDescription(){
		return description;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getCategory(){
		return category;
	}
}