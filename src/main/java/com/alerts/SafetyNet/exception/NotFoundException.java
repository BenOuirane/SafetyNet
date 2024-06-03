package com.alerts.SafetyNet.exception;

@SuppressWarnings("serial")
public class NotFoundException extends Exception{
	 public NotFoundException() {
			super("Entity not found");
	    }
	 
}
