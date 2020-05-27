package code.DataBaseProject.Response;

import lombok.Data;

@Data
public class SuccessRestResponse {

	private boolean success;

	private long timestamp;

	private String message;

	private Object data;

}
