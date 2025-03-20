package vn.devpro.javaweb30.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.javaweb30.dto.UserSignup;
import vn.devpro.javaweb30.model.Role;
import vn.devpro.javaweb30.model.User;
import vn.devpro.javaweb30.service.RoleService;
import vn.devpro.javaweb30.service.UserService;

@Controller
public class LoginController extends BaseController {
	
	@Autowired 
	private UserService userService; 
	@Autowired
	private RoleService roleService; 
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
		return "signup";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>>   register(
			final HttpServletRequest request,
			@RequestBody UserSignup userSignup) {
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		
		User user = 
				userService.getUserByUsername(userSignup.getUsername().trim());
		
		if (user != null) {
			jsonResult.put("code", 404); 
			jsonResult.put("message", "Username da duoc su dung, xin tao khac");
			return ResponseEntity.ok(jsonResult);

		}
		
		if (StringUtils.isEmpty(userSignup.getPassword()) ||
			!userSignup.getPassword().equals(userSignup.getRetypepassword())) {
			jsonResult.put("code", 404); 
			jsonResult.put("message", "Retype password chua dung"); 
			return ResponseEntity.ok(jsonResult);

		}
		List<Role> roles = roleService.getRoleByName("CUSTOMER"); 
		String password = 
				new BCryptPasswordEncoder(4).encode(userSignup.getPassword());
		user = new User();
		
		for(Role role: roles) {
			user.addRelationalUserRole(role);
		}
//		user.setRoles(roles);
		user.setPassword(password);
		user.setUsername(userSignup.getUsername());
		user.setEmail(userSignup.getEmail());
		user.setMobile(userSignup.getMobile());
		
		userService.saveOrUpdate(user);
		jsonResult.put("code", 400); 
		jsonResult.put("message", "Dang ky thanh cong, moi ve trang login"); 
				
		return ResponseEntity.ok(jsonResult);
	}
}
