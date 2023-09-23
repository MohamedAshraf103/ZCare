package org.ZCare.DTO;

public class Mail {
public String to;
public String code;
public Mail() {}
public Mail(String to, String code) {
	super();
	this.to = to;
	this.code = code;
}
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
}
