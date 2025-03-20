package vn.devpro.javaweb30.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import vn.devpro.javaweb30.dto.Cart;
import vn.devpro.javaweb30.model.User;

@Controller
public class BaseController {
	
	@ModelAttribute("title")
	public String title() {
		return "Javaweb-30";
	}
	
	@ModelAttribute("totalCartProducts")
	public BigInteger totalCartProducts(
			final HttpServletRequest request) {
		BigInteger total = BigInteger.ZERO;
		//Lay gio hang
		HttpSession session = request.getSession();
		if (session.getAttribute("cart") != null) {
			Cart cart = (Cart)session.getAttribute("cart");
			total = cart.totalCartProduct();
		}
		return total;
	}
	//lay thong tin cua user dang nhap 
	@ModelAttribute("loginedUser")
	public User getLoginedUser() {
		
		Object loginedUser = 
				SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		
		if (loginedUser !=null && loginedUser instanceof UserDetails) {
			User user = (User) loginedUser;
			return user;
		}
		return new User(); 
				
				
	}
	//kiem tra da login hay chua? 
	@ModelAttribute("isLogined")
	public boolean isLogined() {
		Object loginedUser = 
				SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		
		if (loginedUser != null && loginedUser instanceof UserDetails) {
			return true;
		}return false; 
	}
}
