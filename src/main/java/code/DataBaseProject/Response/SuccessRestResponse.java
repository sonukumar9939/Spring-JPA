package code.DataBaseProject.Response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SuccessRestResponse {

	private boolean success;

	private LocalDateTime date;

	private String message;

	private Object data;

}
