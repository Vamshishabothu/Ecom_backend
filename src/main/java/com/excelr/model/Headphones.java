package com.excelr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Headphones {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pid;
	private String pname;
	private int pcost;
	private int pqty;
	private String pimage;
	public Headphones() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Headphones(int pid, String pname, int pcost, int pqty, String pimage) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pcost = pcost;
		this.pqty = pqty;
		this.pimage = pimage;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPcost() {
		return pcost;
	}
	public void setPcost(int pcost) {
		this.pcost = pcost;
	}
	public int getQty() {
		return pqty;
	}
	public void setQty(int qty) {
		this.pqty = qty;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	@Override
	public String toString() {
		return "Headphones [pid=" + pid + ", pname=" + pname + ", pcost=" + pcost + ", pqty=" + pqty + ", pimage=" + pimage
				+ "]";
	}
	
}
