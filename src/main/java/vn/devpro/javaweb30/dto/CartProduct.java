package vn.devpro.javaweb30.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CartProduct {
	private int id;
	private String name;
	private BigInteger quantity;
	private BigDecimal price;
	private String avatar;
	
	//Phuong thuc tinh thanh tien = so luong * don gia
	public BigDecimal totalPrice() {
		return this.price.multiply(new BigDecimal(this.quantity));
	}	
	//Phuong thuc tang/giam them so luong cua san pham
	
	public void updateQuantity(BigInteger quantity) {
		this.quantity = this.quantity.add(quantity);
	}
	
	public CartProduct() {
		super();
	}
	
	public CartProduct(int id, String name, BigInteger quantity, BigDecimal price, String avatar) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.avatar = avatar;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigInteger getQuantity() {
		return quantity;
	}
	public void setQuantity(BigInteger quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
