package com.ssafy.subpjt.vo;

public class Menu {
	private String img;
	private String id;
	private String name;
	private String tag;
	private String tip;
	private int cost;
	
	
	
	public Menu() {
		super();
	}
	public Menu(String img, String id, String name, String tag, String tip, int cost) {
		super();
		this.img = img;
		this.id = id;
		this.name = name;
		this.tag = tag;
		this.tip = tip;
		this.cost = cost;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	@Override
	public String toString() {
		return "Menu [img=" + img + ", id=" + id + ", name=" + name + ", tag=" + tag + ", tip=" + tip + ", cost=" + cost
				+ "]";
	}
	
	
	
	
	
}
