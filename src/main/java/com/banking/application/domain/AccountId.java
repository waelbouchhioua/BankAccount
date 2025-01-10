package com.banking.application.domain;

public class AccountId {

	private long id;

	private static long lastId = 1;

	public AccountId() {
		this.id = lastId++;
	}

	public AccountId(long id) {
		this.id = id;
	}

	public long getLastId() {
		return lastId;
	}

	public long getId() {
		return id;
	}

}
