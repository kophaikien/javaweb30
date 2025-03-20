package vn.devpro.javaweb30.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_sale_order_product")
public class SaleOrderProduct extends BaseModel {
	@Column(name = "quantity", nullable = true)
	private Integer quantity;
	
	@Column(name = "price", nullable = true)
	private BigDecimal price;
	
	@Column(name = "description", length = 500, nullable = true)
	private String description;
	
	@Column(name = "product_name", length = 300, nullable = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Mapping many-to-one: tbl_sale_order_product-to-tbl_product
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product;

	// Mapping many-to-one: tbl_sale_order_product-to-tbl_sale_order
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sale_order_id")
	private SaleOrder saleOrder;

	public SaleOrderProduct() {
		super();
	}

	public SaleOrderProduct(Integer id, Date createDate, Date updateDate, Boolean status, Integer quantity,
			BigDecimal price, String description, String name, Product product, SaleOrder saleOrder) {
		super(id, createDate, updateDate, status);
		this.quantity = quantity;
		this.price = price;
		this.description = description;
		this.name = name;
		this.product = product;
		this.saleOrder = saleOrder;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public SaleOrder getSaleOrder() {
		return saleOrder;
	}

	public void setSaleOrder(SaleOrder saleOrder) {
		this.saleOrder = saleOrder;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
