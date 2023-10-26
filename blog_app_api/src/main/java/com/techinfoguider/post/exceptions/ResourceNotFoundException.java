package com.techinfoguider.post.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	private String resource;
	private String fieldName;
	private Integer fieldId;
	public ResourceNotFoundException(String resource, String fieldName, Integer fieldId) {
		super(String.format("%s not found with %s: %s", resource, fieldName, fieldId));
		this.resource = resource;
		this.fieldName = fieldName;
		this.fieldId = fieldId;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Integer getFieldId() {
		return fieldId;
	}
	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}
	
	
	
}
