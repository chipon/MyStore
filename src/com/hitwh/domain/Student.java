package com.hitwh.domain;

public class Student {
	int sno,sdept,smajor,sgrade;
    String sname;
    String snickname;
    String pwd,ssignature;
	public Student(int sno, int sdept, int smajor, int sgrade, String sname, String snickname, String pwd,
			String ssignature) {
		super();
		this.sno = sno;
		this.sdept = sdept;
		this.smajor = smajor;
		this.sgrade = sgrade;
		this.sname = sname;
		this.snickname = snickname;
		this.pwd = pwd;
		this.ssignature = ssignature;
	}
}
