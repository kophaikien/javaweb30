package vn.devpro.javaweb30.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/admin/role")
public class RoleController {
	
	@RequestMapping(value="view", method = RequestMethod.GET)
	public String roleView() { 
		return "/backend/role/role-list"; 
	}
}
