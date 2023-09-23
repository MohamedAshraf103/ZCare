package org.ZCare.DTO;

public class AccountResponse {
	int result;
	String code;
public AccountResponse() {}
	public AccountResponse(int result,String code) {
		super();
		this.result = result;
		this.code=code;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
