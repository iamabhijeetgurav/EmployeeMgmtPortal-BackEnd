package com.cybage.entity;

public class ResumeSummary {

	String response;
	String errorResponse;
	String summary;

	public ResumeSummary() {

	}

	public ResumeSummary(String response, String summary, String errorResponse) {
		super();
		this.response = response;
		this.summary = summary;
		this.errorResponse = errorResponse;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(String errorResponse) {
		this.errorResponse = errorResponse;
	}

	@Override
	public String toString() {
		return "ResumeSummary [response=" + response + ", errorResponse=" + errorResponse + ", summary=" + summary
				+ "]";
	}

}
