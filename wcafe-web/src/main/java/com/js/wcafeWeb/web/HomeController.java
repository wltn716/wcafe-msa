package com.js.wcafeWeb.web;

import java.util.List;

import com.js.wcafeWeb.client.OrderClient;
import com.js.wcafeWeb.client.ProductClient;
import com.js.wcafeWeb.dto.Detail;
import com.js.wcafeWeb.dto.Order;
import com.js.wcafeWeb.dto.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class HomeController {
	
	@Autowired
    private OrderClient orderClient;
	
	@Autowired
	private ProductClient productClient;

	@Autowired
	Environment env;

	@GetMapping("/")
    public ModelAndView index(final ModelAndView mv) {
		mv.setViewName("index");

		mv.addObject("categories", productClient.getMenu());

		int waitingBevN = 0, waitingTime = 0;
		final List<Order> orders = orderClient.getNotServedYet();
		for (final Order order : orders) {
			for (final Detail detail : order.getDetails()) {
				final Product product = productClient.find(detail.getProductId());
				waitingTime += (product.getSeconds() * detail.getQuantity());
				waitingBevN += detail.getQuantity();
			}
		}

		final List<Order> recent = orderClient.recent("helloworld");
		for (final Order order : recent) {
			for (final Detail detail : order.getDetails()) {
				detail.setProduct(productClient.find(detail.getProductId()));
			}
		}
		mv.addObject("recent", recent);
		mv.addObject("waitingBevN", waitingBevN);
		mv.addObject("waitingTime", waitingTime);

		mv.addObject("producturl", env.getProperty("feign.product-api.url"));
		mv.addObject("orderurl", env.getProperty("feign.order-api.url"));

		return mv;
	}

	@GetMapping("/admin/management")
	public ModelAndView admin(final ModelAndView mv) {
		mv.setViewName("admin");
		final List<Order> orders = orderClient.getNotServedYet();
		for (final Order order : orders) {
			for (final Detail detail : order.getDetails()) {
				detail.setProduct(productClient.find(detail.getProductId()));
			}
		}
		mv.addObject("orderurl", env.getProperty("feign.order-api.url"));
		mv.addObject("orders",orders);
		return mv;
	}
}
