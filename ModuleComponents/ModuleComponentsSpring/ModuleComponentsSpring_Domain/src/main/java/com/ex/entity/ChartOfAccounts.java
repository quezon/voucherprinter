package com.ex.entity;

public class ChartOfAccounts{
	private int code;
	private String accountName;
	private String accountFs;
	private String accountFsGroup;
	private String accountFsSubgroup;
	private String normally;

	public ChartOfAccounts() {
	}

	public ChartOfAccounts(int code) {
		this.code = code;
	}

	public ChartOfAccounts(int code, String accountName, String accountFs, String accountFsGroup,
			String accountFsSubgroup, String normally) {
		this.code = code;
		this.accountName = accountName;
		this.accountFs = accountFs;
		this.accountFsGroup = accountFsGroup;
		this.accountFsSubgroup = accountFsSubgroup;
		this.normally = normally;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountFs() {
		return this.accountFs;
	}

	public void setAccountFs(String accountFs) {
		this.accountFs = accountFs;
	}

	public String getAccountFsGroup() {
		return this.accountFsGroup;
	}

	public void setAccountFsGroup(String accountFsGroup) {
		this.accountFsGroup = accountFsGroup;
	}

	public String getAccountFsSubgroup() {
		return this.accountFsSubgroup;
	}

	public void setAccountFsSubgroup(String accountFsSubgroup) {
		this.accountFsSubgroup = accountFsSubgroup;
	}

	public String getNormally() {
		return this.normally;
	}

	public void setNormally(String normally) {
		this.normally = normally;
	}

}
