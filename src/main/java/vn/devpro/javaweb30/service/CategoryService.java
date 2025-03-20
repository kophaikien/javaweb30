package vn.devpro.javaweb30.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import vn.devpro.javaweb30.model.Category;

@Service
public class CategoryService extends BaseService<Category> {
	
	@Override
	public Class<Category> clazz() {
		// TODO Auto-generated method stub
		return Category.class;
	}
	
	public List<Category> findAllActive() {
		String sql = "SELECT * FROM tbl_category WHERE status = 1";
		return super.executeNativeSql(sql);
	}
	
	@Transactional
	public void deleteCategoryById(int id) {
		super.deleteById(id);
	}
}
