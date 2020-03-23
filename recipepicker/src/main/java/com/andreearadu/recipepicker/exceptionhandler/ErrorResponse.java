package com.andreearadu.recipepicker.exceptionhandler;

import java.time.LocalDateTime;

public class ErrorResponse {

	private LocalDateTime time;
	private int status;
	private String error;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

}
