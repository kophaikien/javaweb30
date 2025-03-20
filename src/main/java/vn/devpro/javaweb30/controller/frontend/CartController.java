package vn.devpro.javaweb30.controller.frontend;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.javaweb30.controller.BaseController;
import vn.devpro.javaweb30.dto.Cart;
import vn.devpro.javaweb30.dto.CartProduct;
import vn.devpro.javaweb30.dto.Jw30Contant;
import vn.devpro.javaweb30.model.Product;
import vn.devpro.javaweb30.model.SaleOrder;
import vn.devpro.javaweb30.model.SaleOrderProduct;
import vn.devpro.javaweb30.model.User;
import vn.devpro.javaweb30.service.ProductService;
import vn.devpro.javaweb30.service.SaleOrderService;

@Controller
public class CartController extends BaseController implements Jw30Contant {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SaleOrderService saleOrderService;
	
	@RequestMapping(value = "/add-to-cart", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addToCart(
			@RequestBody CartProduct cartProduct,
			final HttpServletRequest request) {
		
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		
		//Kiem tra so luong
		if (cartProduct.getQuantity().intValue() < 1) {
			jsonResult.put("code", 120);
			jsonResult.put("message", "Số lượng không hợp lệ");
		}
		else {
			//Kiem tra co gio hang chua
			Cart cart = new Cart();
			HttpSession session = request.getSession();
			if (session.getAttribute("cart") == null) {//Chua co gio hang
				//Khoi tao gio hang
				session.setAttribute("cart", cart);
			}
			else {
				cart = (Cart) session.getAttribute("cart");
			}
			
			//Them san pham vao gio hang
			int index = cart.findProductById(cartProduct.getId());
			if (index == -1) {//San pham chua co trong gio hang
				//Lay sp trong db
				Product product = productService.getById(cartProduct.getId());
				cartProduct.setAvatar(product.getAvatar());
				cartProduct.setPrice(product.getPrice());
				
				cart.getCartProducts().add(cartProduct);
			}
			else {//San pham da co trong gio hang
				cart.getCartProducts().get(index).updateQuantity(cartProduct.getQuantity());
			}
			
			jsonResult.put("code", 420);
			jsonResult.put("message", "Đã thêm " + cartProduct.getQuantity() + 
					" '" + cartProduct.getName() + "' vào giỏ hàng");
			//Tổng số sản phẩm trong giỏ hàng
			jsonResult.put("totalCartProducts", cart.totalCartProduct());
			
		}
		return ResponseEntity.ok(jsonResult);
	}
	
	@RequestMapping(value = "/cart-view", method = RequestMethod.GET)
	public String cartView(final HttpServletRequest request, final Model model){
		
		//Lay gio hang
		HttpSession session = request.getSession();
		String message = "";
		BigInteger totalCartProduct = BigInteger.ZERO;
		BigDecimal totalCartPrice = BigDecimal.ZERO;
		if(session.getAttribute("cart")==null) {
			message = "Ban chua co san pham nao";
		}else {
			Cart cart = (Cart)session.getAttribute("cart");
			totalCartProduct=cart.totalCartProduct();
			totalCartPrice=cart.totalCartPrice();
			message="Co " +totalCartProduct+"san pham trong gio hang";
		}
		
		model.addAttribute("totalCartPrice", totalCartPrice);
		model.addAttribute("message", message);
		
		return "frontend/cart-view";
	}
	
	@RequestMapping(value = "/update-product-quantity", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateProductQuantity(
			@RequestBody CartProduct cartProduct,
			final HttpServletRequest request){
		Map<String, Object> jsonResult= new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		//Tim san pham trong gio hang
		int index=cart.findProductById(cartProduct.getId());
		BigInteger newQuantity=cart.getCartProducts().get(index).getQuantity();
		
		//TH giam so luong
		if(cartProduct.getQuantity().intValue()==-1) {
			if(newQuantity.intValue()>1) {
				newQuantity=newQuantity.add(new BigInteger("-1"));
				cart.getCartProducts().get(index).setQuantity(newQuantity);
			}
		}
		else {
			//Truong hop tang
			newQuantity=newQuantity.add(new BigInteger("1"));
			cart.getCartProducts().get(index).setQuantity(newQuantity);
		}
		
		jsonResult.put("newQuantity", newQuantity);

		jsonResult.put("totalPrice", toCurrency(cart.getCartProducts().get(index).totalPrice()));
		jsonResult.put("totalCartProducts", cart.totalCartProduct());
		jsonResult.put("totalCartPrice", toCurrency(cart.totalCartPrice()));
		jsonResult.put("productId", cartProduct.getId());
		
		return ResponseEntity.ok(jsonResult);
	}
	
	//Chuyen so sang chuoi dinh dang tien te
	public StringBuilder toCurrency(BigDecimal money) {
		StringBuilder str = new StringBuilder(""+money.longValue());
		int i =str.length()-3;
		int j = 0;
		while (i>0) {
			if(j%3==0) {
				str.insert(i, ",");
			}
			i--;
			j++;
		}
		return str;
	}
	
	@RequestMapping(value = "/delete/{productId}", method =  RequestMethod.GET)
	public String deleteProductInCart(@PathVariable("productId") int productId,
			final HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		if(session.getAttribute("cart")!=null) {
			Cart cart = (Cart)session.getAttribute("cart");
			int index= cart.findProductById(productId);
			if(index!=-1) {
				cart.getCartProducts().remove(index);
			}
		}	
		return "redirect:/cart-view";
	}
	
	@RequestMapping(value = "/place-order", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> placeOrder(
			final HttpServletRequest request,
			@RequestBody User user){
		
		Map<String, Object> jsonResult= new HashMap<String, Object>();
		if(user.getName()==null||StringUtils.isEmpty(user.getName())) {
			jsonResult.put("code", 404);
			jsonResult.put("message", "Ban chua nhap ho ten, xin nhap");
		}else if(user.getMobile()==null||StringUtils.isEmpty(user.getMobile())) {
			jsonResult.put("code", 405);
			jsonResult.put("message", "Ban chua nhap dien thoai, xin nhap");
		}
		else {
			
			HttpSession session = request.getSession();
			if(session.getAttribute("cart")==null) {
				jsonResult.put("code", 406);
				jsonResult.put("message", "Ban chua co san pham nao trong gio hang");
			}else {
				Cart cart= (Cart)session.getAttribute("cart");
				if(cart.getCartProducts()==null||cart.getCartProducts().size()<=0) {
					jsonResult.put("code", 406);
					jsonResult.put("message", "Ban chua co san pham nao trong gio hang");
				}else {
					
					Calendar calendar = Calendar.getInstance();
					int day= calendar.get(Calendar.DAY_OF_MONTH);
					int month= calendar.get(Calendar.MONTH)+1;
					int year = calendar.get(Calendar.YEAR);
					int hour= calendar.get(Calendar.HOUR_OF_DAY);
					int minute= calendar.get(Calendar.MINUTE);
					int second = calendar.get(Calendar.SECOND);
					String code= user.getMobile()+ year+month+day+hour+minute+second;
					
					SaleOrder saleOrder = new SaleOrder();
					saleOrder.setCode(code);
					saleOrder.setCreateDate(new Date());
					saleOrder.setCustomerName(user.getName());
					saleOrder.setCustomerMobile(user.getMobile());
					saleOrder.setCustomerEmail(user.getEmail());
					saleOrder.setCustomerAddress(user.getAddress());
					
					user.setId(4);
					saleOrder.setUser(user);
					saleOrder.setTotal(cart.totalCartPrice());
					saleOrder.setUserCreateSaleOrder(user);
					//Luu cac san pham trong gio hang vao tbl_sale_order_product 
					for(CartProduct cartProduct: cart.getCartProducts()) {
						SaleOrderProduct saleOrderProduct = new SaleOrderProduct();
						saleOrderProduct.setName(cartProduct.getName());
						saleOrderProduct.setQuantity(cartProduct.getQuantity().intValue());
						saleOrderProduct.setPrice(cartProduct.getPrice());
						
						Product product = productService.getById(cartProduct.getId());
						saleOrderProduct.setProduct(product);
						
						saleOrderProduct.setSaleOrder(saleOrder);
						
						saleOrder.addRelationalSaleOrderProduct(saleOrderProduct);
					}
					
					saleOrderService.saveOrder(saleOrder);
					jsonResult.put("code", 200);
					jsonResult.put("message", "Ban dat hang thanh cong, chung toi se lien lac voi ban som nhat!");
					
					//Xoa gio hang sau khi dat hang thanh cong
					cart = new Cart();
					session.setAttribute("cart", cart);
				}
			}
			
			
		}
		return ResponseEntity.ok(jsonResult);
		
	}
	
}
