package vn.devpro.javaweb30.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import vn.devpro.javaweb30.dto.Jw30Contant;
import vn.devpro.javaweb30.dto.SearchModel;
import vn.devpro.javaweb30.model.Product;
import vn.devpro.javaweb30.model.ProductImage;

@Service
public class ProductService extends BaseService<Product> implements Jw30Contant {
	
	@Override
	public Class<Product> clazz() {
		// TODO Auto-generated method stub
		return Product.class;
	}
	
	public List<Product> findAllActive() {
		String sql = "SELECT * FROM tbl_product WHERE status = 1";
		return super.executeNativeSql(sql);
	}
	
	//Kiem tra file co ton tai hay khong?
	public boolean isExistFile(MultipartFile file) {
		if (file != null && !StringUtils.isEmpty(file.getOriginalFilename())) {
			return true;
		}
		return false;
		
	}
	
	//Kiem tra file co ton tai hay khong?
		public boolean isExistFiles(MultipartFile[] files) {
			if (files != null && files.length > 0) {
				return true;
			}
			return false;
			
		}
	
	@Transactional
	public Product saveProduct(Product product, MultipartFile avatarFile,
			MultipartFile[] imageFiles) throws IOException {
		
		//Kiem tra xem co upload avatar khong?
		if (isExistFile(avatarFile)) {//co upload
			//Luu file vao thu muc Product/Avatar
			String path = FOLDER_UPLOAD + "Product/Avatar/" 
							+ avatarFile.getOriginalFilename();
			
			File file = new File(path);
			avatarFile.transferTo(file);
			
			//Luu duong dan vao DB
			product.setAvatar("Product/Avatar/" + avatarFile.getOriginalFilename());
		}
		
		//Kiem tra xem co upload images khong?
		if (isExistFiles(imageFiles)) {//Co upload
			for (MultipartFile image : imageFiles) {
				if (isExistFile(image)) {
					//Luu file vao thu muc Product/Image
					String path = FOLDER_UPLOAD + "Product/Image/" 
							+ image.getOriginalFilename();
			
					File file = new File(path);
					image.transferTo(file);
					//Luu duong dan vao bang tbl_product_image
					ProductImage productImage = new ProductImage();
					productImage.setPath("Product/Image/" + image.getOriginalFilename());
					productImage.setTitle(image.getOriginalFilename());
					productImage.setCreateDate(new Date());
					productImage.setStatus(true);
					
					productImage.setProduct(product);
					product.addRelationalProductImage(productImage);
				}
			}
		}
		if (product.getPrice() == null) {
			product.setPrice(BigDecimal.ZERO);
		}
		if (product.getSalePrice() == null) {
			product.setSalePrice(BigDecimal.ZERO);
		}
		return saveOrUpdate(product);
	}
	
	@Transactional
	public Product saveEditProduct(Product product, MultipartFile avatarFile,
			MultipartFile[] imageFiles) throws IOException {
		
		//Kiem tra xem co upload avatar khong?
		if (isExistFile(avatarFile)) {//co upload
			
			//Kiem tra xem co avatar cu khong?
			if (product.getAvatar() != null && !StringUtils.isEmpty(product.getAvatar())) {
				//Co thi phai xoa avatar cu
				String path = FOLDER_UPLOAD + product.getAvatar();
				File file = new File(path);
				file.delete();
				
			}
			//Luu file moi
			product.setAvatar("Product/Avatar/" + avatarFile.getOriginalFilename());
			String path = FOLDER_UPLOAD + "Product/Avatar/" 
					+ avatarFile.getOriginalFilename();
	
			File file = new File(path);
			avatarFile.transferTo(file);
		}
		
		//Kiem tra xem co upload images khong?
		if (isExistFiles(imageFiles)) {//Co upload
			for (MultipartFile image : imageFiles) {
				if (isExistFile(image)) {
					//Luu file vao thu muc Product/Image
					String path = FOLDER_UPLOAD + "Product/Image/" 
							+ image.getOriginalFilename();
			
					File file = new File(path);
					image.transferTo(file);
					//Luu duong dan vao bang tbl_product_image
					ProductImage productImage = new ProductImage();
					productImage.setPath("Product/Image/" + image.getOriginalFilename());
					productImage.setTitle(image.getOriginalFilename());
					productImage.setCreateDate(new Date());
					productImage.setStatus(true);
					
					productImage.setProduct(product);
					product.addRelationalProductImage(productImage);
				}
			}
		}
		if (product.getPrice() == null) {
			product.setPrice(BigDecimal.ZERO);
		}
		if (product.getSalePrice() == null) {
			product.setSalePrice(BigDecimal.ZERO);
		}
		return saveOrUpdate(product);
	}
	
	@Transactional
	public void inactiveProduct(Product product) {
		super.saveOrUpdate(product);
	}
	
	public List<Product> search(SearchModel searchModel) {
		String sql = "SELECT * FROM tbl_product p WHERE 1=1";
		
		//Tim theo status
		if (searchModel.getStatus() != 2) {
			sql += " AND p.status=" + searchModel.getStatus();
		}
		
		//Tim theo catgory
		if (searchModel.getCategoryId() != 0) {
			sql += " AND p.category_id=" + searchModel.getCategoryId();
		}
		
		//Tim theo keyword
		String keyword = searchModel.getKeyword(); 
		if (keyword != null) {
			sql += " AND (LOWER(p.name) like '%" + keyword + "%'" +
					" OR LOWER(p.short_description) like '%" + keyword + "%'" +
					" OR LOWER(p.seo) like '%" + keyword + "%')";
		}
		
		//Tim tu ngay den ngay
		String date1 = searchModel.getBeginDate();
		String date2 = searchModel.getEndDate();
		if (date1 != null && date2 != null) {
			sql += " AND p.create_date BETWEEN '" + date1 + "' AND '" + date2 + "'";
		}
		
		System.out.println(sql);
 		return super.executeNativeSql(sql);
	}
}
