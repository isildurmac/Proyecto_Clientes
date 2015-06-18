package com.chakray.samples.jaxws;

public class CustomExceptionFaultBean {
    private String message;
    
    public CustomExceptionFaultBean() {
    }
    public CustomExceptionFaultBean(String message) {
        this.message = message;
    }
 
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
	this.message = message;
    }	
}
