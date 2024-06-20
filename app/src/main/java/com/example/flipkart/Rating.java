package com.example.flipkart;

import com.google.gson.annotations.SerializedName;

public class Rating{

	@SerializedName("rate")
	private Object rate;

	@SerializedName("count")
	private int count;

	public Object getRate(){
		return rate;
	}

	public int getCount(){
		return count;
	}
	public float getRateAsFloat() {
		if (rate instanceof Number) {
			return ((Number) rate).floatValue();
		} else {
			return 0.0f; // Default value if rate is not a number
		}
	}
}