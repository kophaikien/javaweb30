package vn.devpro.javaweb30.controller.backend;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.javaweb30.controller.BaseController;
import vn.devpro.javaweb30.model.Category;
import vn.devpro.javaweb30.model.User;
import vn.devpro.javaweb30.service.CategoryService;
import vn.devpro.javaweb30.service.UserService;

@Controller
@RequestMapping("/admin/category/")
public class CategoryAdminCotroller extends BaseController {
	
	//Khai bao service
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view(final Model model) {
		
		//Lay du lieu tu db de tron voi view tra ve cho browser
//		List<Category> categories = categoryService.findAll();
		List<Category> categories = categoryService.findAllActive();
		//Day du lieu sang view
		model.addAttribute("categories", categories);
		
		return "backend/category/category-list";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(final Model model) {
		
		//Lay du lieu tu db de tron voi view tra ve cho browser
		List<User> users = userService.findAll();
		//Day du lieu sang view
		model.addAttribute("users", users);
		
		Category category = new Category();
		
		category.setCreateDate(new Date());
		//category.setUpdateDate(new Date());
		model.addAttribute("category", category);
		
		return "backend/category/category-add";
	}
	
	@RequestMapping(value = "add-save", method = RequestMethod.POST)
	public String addSave(
			@ModelAttribute("category") Category category) {
		
		categoryService.saveOrUpdate(category);
		
		return "redirect:/admin/category/add";
	}
	
	@RequestMapping(value = "edit/{categoryId}", method = RequestMethod.GET)
	public String edit(final Model model,
			@PathVariable int categoryId) {
		
		//Lay du lieu tu db de tron voi view tra ve cho browser
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		//Lay ban ghi category tu DB day vao form
		
		Category category = categoryService.getById(categoryId);
		
		//category.setCreateDate(new Date());
		category.setUpdateDate(new Date());
		model.addAttribute("category", category);
		
		return "backend/category/category-edit";
	}
	
	@RequestMapping(value = "edit-save", method = RequestMethod.POST)
	public String editSave(
			@ModelAttribute("category") Category category) {
		
		categoryService.saveOrUpdate(category);
		
		return "redirect:/admin/category/view";
	}
	
//	@RequestMapping(value = "delete/{categoryId}", method = RequestMethod.GET)
//	public String delete(@PathVariable int categoryId) {
//		
//		categoryService.deleteCategoryById(categoryId);
//		
//		return "redirect:/admin/category/view";
//	}
	
	@RequestMapping(value = "delete/{categoryId}", method = RequestMethod.GET)
	public String delete(@PathVariable int categoryId) {
		
		//Lay du lieu trong DB
		Category category = categoryService.getById(categoryId);
		
		category.setStatus(false);
		categoryService.saveOrUpdate(category);
		
		return "redirect:/admin/category/view";
	}
}

