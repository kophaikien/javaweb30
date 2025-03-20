package vn.devpro.javaweb30.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.devpro.javaweb30.model.ProductImage;

@Service
public class ProductImageService extends BaseService<ProductImage> {

	@Override
	public Class<ProductImage> clazz() {
		// TODO Auto-generated method stub
		return ProductImage.class;
	}
	
	//Xoa anh san pham theo product_id
	
	@Transactional
	public void deleteImageByProductId(int productId) {
		String sql = "DELETE FROM tbl_product_image where product_id=" + productId;
		super.executeNativeSql(sql);
	}
	
	public List<ProductImage> getProductImageByProductId(int productId) {
		String sql = "SELECT * FROM tbl_product_image WHERE product_id=" + productId;
		return super.executeNativeSql(sql);
	}
}
