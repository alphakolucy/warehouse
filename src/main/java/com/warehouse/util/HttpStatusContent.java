package com.warehouse.util;

import com.warehouse.util.enums.OutputState;

public class HttpStatusContent { 
    private String message;

    public HttpStatusContent(OutputState state) { 
    	this.message = state.getTxt();
	}
    
    public HttpStatusContent(OutputState state, String msg) {
		// TODO Auto-generated constructor stub  
    	this.message = msg;
	}  
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
