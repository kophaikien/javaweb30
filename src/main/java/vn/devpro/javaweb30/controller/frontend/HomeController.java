package vn.devpro.javaweb30.controller.frontend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.javaweb30.controller.BaseController;
import vn.devpro.javaweb30.model.Product;
import vn.devpro.javaweb30.model.ProductImage;
import vn.devpro.javaweb30.service.ProductImageService;
import vn.devpro.javaweb30.service.ProductService;

@Controller
public class HomeController extends BaseController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductImageService productImageService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String viewHomePage(final Model model) {
		
		List<Product> products = productService.findAllActive();
		model.addAttribute("products", products);
		
		return "frontend/index";
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String viewCategoryPage() {
		return "frontend/category";
	}
	
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
	public String viewProductPage(@PathVariable int productId,
			final Model model) {
		//Lay sp trong DB
		Product product = productService.getById(productId);
		//Lay danh sach anh tuong ung voi san pham
		List<ProductImage> productImages = 
				productImageService.getProductImageByProductId(productId);
		
		model.addAttribute("product", product);
		model.addAttribute("productImages", productImages);
		
		return "frontend/product";
	}

}
