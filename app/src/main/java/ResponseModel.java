import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel{

	@SerializedName("ResponseModel")
	private List<ResponseModelItem> responseModel;

	public List<ResponseModelItem> getResponseModel(){
		return responseModel;
	}
}
