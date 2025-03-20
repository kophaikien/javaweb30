package vn.devpro.javaweb30.controller.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.javaweb30.model.User;
import vn.devpro.javaweb30.service.UserService; 
@Controller
@RequestMapping("/admin/user/")
public class UserAdminController {

	@Autowired 
	private UserService userService; 
	
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String viewUser (final Model model) {
		List<User> users = userService.findAllActive(); 
		model.addAttribute("users", users);
		return "backend/user/user-list"; 
	}
}
