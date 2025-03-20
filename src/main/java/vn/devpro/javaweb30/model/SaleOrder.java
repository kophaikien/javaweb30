package vn.devpro.javaweb30.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_sale_order")
public class SaleOrder extends BaseModel {

	@Column(name = "code", length = 60, nullable = false)
	private String code;
	
	@Column(name = "total", nullable = true)
	private BigDecimal total = BigDecimal.ZERO;
	
	@Column(name = "customer_name", length = 300, nullable = true)
	private String customerName;
	
	@Column(name = "customer_mobile", length = 120, nullable = true)
	private String customerMobile;
	
	@Column(name = "customer_email", length = 120, nullable = true)
	private String customerEmail;
	
	@Column(name = "customer_address", length = 300, nullable = true)
	private String customerAddress;
	
	//Mapping one-to-many: tbl_sale_order-to-tbl_sale_order_product
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "saleOrder")
	private List<SaleOrderProduct> saleOrderProducts = new ArrayList<SaleOrderProduct>();
	
	//Add and remove elements out of sale-order-product list
	public void addRelationalSaleOrderProduct(SaleOrderProduct saleOrderProduct) {
		saleOrderProducts.add(saleOrderProduct);
		saleOrderProduct.setSaleOrder(this);
	}
	public void removeRelationalSaleOrderProduct(SaleOrderProduct saleOrderProduct) {
		saleOrderProducts.remove(saleOrderProduct);
		saleOrderProduct.setSaleOrder(this);		
	}
	
	//Mapping many-to-one: tbl_sale_order-to-tbl_user
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
//--------------------------------------------------------------------------------------------	

	// Mapping many-to-one: tbl_sale_order-to-tbl_user (for create sale_order)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "create_by")
	private User userCreateSaleOrder;

	// Mapping many-to-one: tbl_sale_order-to-tbl_user (for update sale_order)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "update_by")
	private User userUpdateSaleOrder;
//--------------------------------------------------------------------------------------------
	
	
	public SaleOrder() {
		super();
	}
	
	
	public SaleOrder(Integer id, Date createDate, Date updateDate, Boolean status, String code, BigDecimal total,
		String customerName, String customerMobile, String customerEmail, String customerAddress,
		List<SaleOrderProduct> saleOrderProducts, User user, User userCreateSaleOrder, User userUpdateSaleOrder) {
		super(id, createDate, updateDate, status);
		this.code = code;
		this.total = total;
		this.customerName = customerName;
		this.customerMobile = customerMobile;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.saleOrderProducts = saleOrderProducts;
		this.user = user;
		this.userCreateSaleOrder = userCreateSaleOrder;
		this.userUpdateSaleOrder = userUpdateSaleOrder;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public List<SaleOrderProduct> getSaleOrderProducts() {
		return saleOrderProducts;
	}
	public void setSaleOrderProducts(List<SaleOrderProduct> saleOrderProducts) {
		this.saleOrderProducts = saleOrderProducts;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getUserCreateSaleOrder() {
		return userCreateSaleOrder;
	}
	public void setUserCreateSaleOrder(User userCreateSaleOrder) {
		this.userCreateSaleOrder = userCreateSaleOrder;
	}
	public User getUserUpdateSaleOrder() {
		return userUpdateSaleOrder;
	}
	public void setUserUpdateSaleOrder(User userUpdateSaleOrder) {
		this.userUpdateSaleOrder = userUpdateSaleOrder;
	}
	
	
}
