package vn.devpro.javaweb30.controller.frontend;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.devpro.javaweb30.controller.BaseController;
import vn.devpro.javaweb30.model.Contact;

@Controller

@RequestMapping("/contact/")
public class ContactController extends BaseController {
	
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String viewContactPage() {
		return "frontend/contact";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveContact(final HttpServletRequest request) {
		//Lấy dữ liệu từ view
//		String name = request.getParameter("name");
//		String email = request.getParameter("email");
//		String message = request.getParameter("message");
		
		Contact contact = new Contact();
		
		contact.setName(request.getParameter("name")); //name of input
		contact.setEmail(request.getParameter("email"));
		contact.setMessage(request.getParameter("message"));
		
		System.out.println("Name: " + contact.getName());
		System.out.println("Email: " + contact.getEmail());
		System.out.println("Message: " + contact.getMessage());
		
		return "frontend/contact";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String viewEditPage(final Model model) {
		
//		String name = "Nguyễn Duy Thường";
//		String email = "thuongnd@yahoo.com";
//		String message = "Cửa hàng ship chậm";
		
		Contact contact = new Contact(
				"Nguyễn Duy Thường", "thuongnd@yahoo.com", 
				"0987676543", "Thường Tín - Hà Tây", "Cảm ơn cửa hàng"); 
		//Đẩy dữ liệu sang view
//		model.addAttribute("name", name);
//		model.addAttribute("email", email);
//		model.addAttribute("message", message);
		
		model.addAttribute("contact", contact);
		
		return "frontend/contact-edit";
	}
	
//	@RequestMapping(value = "save-edit", method = RequestMethod.POST)
//	public String saveEditContact(final HttpServletRequest request) {
//		//Lấy dữ liệu từ view
//		String name = request.getParameter("name"); //get by name
//		String email = request.getParameter("email");
//		String message = request.getParameter("message");
//		
//		System.out.println("Name: " + name);
//		System.out.println("Email: " + email);
//		System.out.println("Message: " + message);
//		
//		return "frontend/contact-edit";
//	}
	
	@RequestMapping(value = "save-edit", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> saveEditContactAjax(
			@RequestBody Contact contact) {
		
		//Luu dữ liệu vào DB
		System.out.println(contact.getAddress());
		
		Map<String, String> jsonResult = new HashMap<String, String>();
		jsonResult.put("code", "200");
		jsonResult.put("message", "Dữ liệu của bạn " + contact.getName() + 
							" đã được lưu");
		
		return ResponseEntity.ok(jsonResult);
	}
	
	@RequestMapping(value = "view-sf", method = RequestMethod.GET)
	public String viewContactPageSf(final Model model) {
		
		Contact contact = new Contact();
		model.addAttribute("contact", contact);
		
		return "frontend/contact-sf";
	}
	
	@RequestMapping(value = "save-sf", method = RequestMethod.POST)
	public String saveContactPageSf(
			final Model model,
			@ModelAttribute("contact") Contact contact,
			@RequestParam("contactFile") MultipartFile file) 
			throws IOException {
		
		//Kiem tra xem file co ton tai khong
		if (file != null && !StringUtils.isEmpty(file.getOriginalFilename())) {
			//File co duoc upload
			String path = 
	"D:/02_DevPro/04_SourceCodes/Javaweb/javaweb30/UploadFiles/ContactFile/"
				+ file.getOriginalFilename();
			File fp = new File(path);
			file.transferTo(fp); //Luu file vao thu muc 
			
		}
		return "redirect:view-sf";
	}
	
	@RequestMapping(value = "edit-sf", method = RequestMethod.GET)
	public String editContactPageSf(final Model model) {
		
		Contact contact = new Contact(
				"Nguyễn Ngọc Nhan",
				"nhannn@gmail.com",
				"09786546352",
				"Cầu Diễn - BTL - Hà Nội",
				"Thông báo mời họp"
				);
		model.addAttribute("contact", contact);
		
		return "frontend/contact-edit-sf";
	}
	
	@RequestMapping(value = "save-edit-sf", method = RequestMethod.POST)
	public String saveEditContactPageSf(
			final Model model,
			@ModelAttribute("contact") Contact contact,
			@RequestParam("contactFile") MultipartFile file)
				throws IOException {
		
		//Kiem tra xem file co ton tai khong
				if (file != null && !StringUtils.isEmpty(file.getOriginalFilename())) {
					//File co duoc upload
					//+ Doc file cu, xoa no di
					String path = 
			"D:/02_DevPro/04_SourceCodes/Javaweb/javaweb30/UploadFiles/ContactFile/";
					
					File fp = new File(path);
					String[] list = fp.list();
					path += list[0];
					fp = new File(path);
					fp.delete();
					
					//+ Luu file moi
		
					path = 
			"D:/02_DevPro/04_SourceCodes/Javaweb/javaweb30/UploadFiles/ContactFile/"
							+ file.getOriginalFilename();
					fp = new File(path);
					file.transferTo(fp); //Luu file moi
				}
		System.out.println(contact.getName());
		
		return "redirect:view-sf";
	}

}
