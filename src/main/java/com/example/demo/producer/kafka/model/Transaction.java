package com.example.demo.producer.kafka.model;

public class Transaction {

	private String transactionId;
	private boolean success;
	private String msg;

	public Transaction(String transactionId, boolean success, String msg) {
		super();
		this.transactionId = transactionId;
		this.success = success;
		this.msg = msg;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
