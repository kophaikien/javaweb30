package vn.devpro.javaweb30.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Cart {
	
	private ArrayList<CartProduct> cartProducts = new ArrayList<CartProduct>();

	//Tim hang trong gio
	public int findProductById(int productId) {
		for (int index = 0; index < this.cartProducts.size(); index++) {
			if (cartProducts.get(index).getId() == productId) {
				return index;
			}
		}
		return -1;
	}
	
	//Tinh tong tien cua gio hang
	public BigDecimal totalCartPrice() {
		BigDecimal total = BigDecimal.ZERO;
		for (CartProduct cartProduct : this.cartProducts) {
			total = total.add(cartProduct.totalPrice());
		}
		return total;
	}
	
	//Tong so luong san pham trong gio hang
	public BigInteger totalCartProduct() {
		BigInteger total = BigInteger.ZERO;
		for (CartProduct cartProduct : this.cartProducts) {
			total = total.add(cartProduct.getQuantity());
		}
		return total; 
	}
	
	public Cart() {
		super();
	}

	public Cart(ArrayList<CartProduct> cartProducts) {
		super();
		this.cartProducts = cartProducts;
	}

	public ArrayList<CartProduct> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(ArrayList<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
}
