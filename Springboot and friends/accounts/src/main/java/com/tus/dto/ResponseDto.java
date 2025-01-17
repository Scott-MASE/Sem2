package com.tus.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor 
public class ResponseDto {
	public ResponseDto() {}
	private String statusCode;
	private String statusMsg;
}
