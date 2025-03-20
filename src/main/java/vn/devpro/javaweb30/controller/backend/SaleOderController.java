package vn.devpro.javaweb30.controller.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.javaweb30.controller.BaseController;
import vn.devpro.javaweb30.model.SaleOrder;
import vn.devpro.javaweb30.service.SaleOrderService;

@Controller
@RequestMapping("/admin/order/")
public class SaleOderController extends BaseController {
	
	@Autowired
	private SaleOrderService saleOrderService;
	
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String viewOrder(final Model model) {
		List<SaleOrder> saleOrders = saleOrderService.findAllActive();
		model.addAttribute("saleOrders", saleOrders);
		
		return "backend/order/order-list";
		
	}
	
}
