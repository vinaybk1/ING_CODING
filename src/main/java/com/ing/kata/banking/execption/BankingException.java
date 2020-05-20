package com.ing.kata.banking.execption;

import org.springframework.stereotype.Component;

@Component
public class BankingException extends Exception {
	private String errCode;
	private String errMsg;

	public BankingException() {
	}

	public BankingException(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
