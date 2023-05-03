package com.dbcow.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
	private Integer status;
	private String message;
}
