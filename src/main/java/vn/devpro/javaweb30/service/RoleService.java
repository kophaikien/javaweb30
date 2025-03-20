package vn.devpro.javaweb30.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.devpro.javaweb30.model.Role;

@Service
public class RoleService extends BaseService<Role> {
	@Override
	public Class<Role> clazz() {
		// TODO Auto-generated method stub
		return Role.class;
	}
	
	public List<Role> getRoleByName(String name) {
		String sql = "SELECT * FROM tbl_role WHERE name='"
				+ name + "'"; 
		List<Role> roles = this.executeNativeSql(sql); 
		
		return roles ; 
		
	}
}
