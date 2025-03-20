package vn.devpro.javaweb30.configurer;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//class dung de chuyen cac request khac nhau theo role
public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	// dieu huong den request
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy(); 
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		handle(request, response, authentication); 
		clearAuthenticationAttributes(request); 
	}
	
	// Dua vao role cua user login de xac dinh request chuyen den (redirect)
	protected void handle(HttpServletRequest request, HttpServletResponse response, 
		Authentication authentication) throws IOException{
			String targetUrl = determineTargetUrl(authentication); // lay url theo role cua user login
			if (response.isCommitted()) {
				return; 
		}
			//System.out.println("targetUrl: " + targetUrl); 
			redirectStrategy.sendRedirect(request, response, targetUrl);// dieu huong target URL
			
	}
	
	// lay role cua user va tra ve Url(action) tuong ung voi authentication
	protected String determineTargetUrl (final Authentication authentication) throws IllegalStateException {
			Map<String , String> roleTargeUrlMap = new HashMap<String, String>(); 
			//key: Role - value: URL
			//key la role name, value la Url (action)
			roleTargeUrlMap.put("ADMIN", "/admin/home/view"); 
			roleTargeUrlMap.put("CUSTOMER", "/index"); 
			final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); 
			//authorities lay trong class User
			for (final GrantedAuthority grantedAuthority: authorities) {
				String authorityName = grantedAuthority.getAuthority(); //role name
				if (roleTargeUrlMap.containsKey(authorityName)) {
		// tra ve target url cua user dang nhap 	
					return roleTargeUrlMap.get(authorityName); 
				}
			}
			throw new IllegalStateException(); 
	}
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false); 
		if (session == null) {
			return; 
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		
	}
}
