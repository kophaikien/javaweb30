package vn.devpro.javaweb30.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.devpro.javaweb30.model.SaleOrder;

@Service
public class SaleOrderService extends BaseService<SaleOrder> {
	
	@Override
	public Class<SaleOrder> clazz() {
		// TODO Auto-generated method stub
		return SaleOrder.class;
	}
	
	@Transactional
	public SaleOrder saveOrder(SaleOrder saleOrder) {
		return saveOrUpdate(saleOrder);
	}
	
	public List<SaleOrder> findAllActive() {
		String sql = "SELECT * FROM tbl_sale_order WHERE status = 1";
		return super.executeNativeSql(sql);
	}

}
