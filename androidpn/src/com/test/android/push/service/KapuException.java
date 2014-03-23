package com.test.android.push.service;

public class KapuException extends Exception {

	private static final long serialVersionUID = -6792009069635572497L;

	public KapuException(){
		super();
	}
	
	public KapuException(String msg){
		super(msg);
	}
	
	public KapuException(String msg,Throwable cause){
		super(msg,cause);
	}
	
	public KapuException(Throwable cause){
		super(cause);
	}
}
