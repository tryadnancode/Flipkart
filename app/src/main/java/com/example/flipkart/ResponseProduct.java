package com.example.flipkart;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseProduct{

	@SerializedName("ResponseProduct")
	private List<ResponseProductItem> responseProduct;

	public List<ResponseProductItem> getResponseProduct(){
		return responseProduct;
	}
}