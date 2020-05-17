package com.js.wcafeWeb.web;

import java.util.List;

import com.js.wcafeWeb.client.OrderClient;
import com.js.wcafeWeb.client.ProductClient;
import com.js.wcafeWeb.client.UserClient;
import com.js.wcafeWeb.dto.Account;
import com.js.wcafeWeb.dto.Detail;
import com.js.wcafeWeb.dto.Order;
import com.js.wcafeWeb.dto.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class HomeController {
	
	@Autowired
	private UserClient userClient;

	@Autowired
    private OrderClient orderClient;
	
	@Autowired
	private ProductClient productClient;

	@GetMapping("/")
    public ModelAndView index(ModelAndView mv) {
		mv.setViewName("index");

		Account currentUser = userClient.getCurrentUserInfo();

		mv.addObject("currentUser",currentUser);
		mv.addObject("categories", productClient.getMenu());
		
		int waitingBevN=0, waitingTime=0;
		List<Order> orders = orderClient.getNotServedYet();
		for(Order order : orders) {
			for(Detail detail : order.getDetails()) {
				Product product = productClient.find(detail.getProductId());
				waitingTime+=(product.getSeconds()*detail.getQuantity());
				waitingBevN+=detail.getQuantity();
			}
		}
		
		List<Order> recent = orderClient.recent(currentUser.getId());
		for(Order order : recent) {
			for(Detail detail : order.getDetails()) {
				detail.setProduct(productClient.find(detail.getProductId()));
			}
		}
		mv.addObject("recent", recent);
		mv.addObject("waitingBevN",waitingBevN);
		mv.addObject("waitingTime",waitingTime);
		
		return mv;
    }
	
	@GetMapping("/admin/management")
	public ModelAndView admin(ModelAndView mv) {
		mv.setViewName("admin");
		List<Order> orders = orderClient.getNotServedYet();
		for(Order order : orders) {
			for(Detail detail : order.getDetails()) {
				detail.setProduct(productClient.find(detail.getProductId()));
			}
		}
		
		mv.addObject("orders",orders);
		return mv;
	}
}
