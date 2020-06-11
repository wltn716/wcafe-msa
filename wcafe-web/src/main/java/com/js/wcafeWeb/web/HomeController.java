package com.js.wcafeWeb.web;

import java.util.ArrayList;
import java.util.List;

import com.js.wcafeWeb.client.OrderClient;
import com.js.wcafeWeb.client.ProductClient;
import com.js.wcafeWeb.dto.Account;
import com.js.wcafeWeb.dto.Category;
import com.js.wcafeWeb.dto.Detail;
import com.js.wcafeWeb.dto.Order;
import com.js.wcafeWeb.dto.Product;
import com.js.wcafeWeb.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
	private AccountService accountService;

	@Autowired
	private OAuth2FeignRequestInterceptor interceptor; 	

	@Autowired
	Environment env;

	@GetMapping("/")
    public ModelAndView index(ModelAndView mv, Authentication authentication) {
		mv.setViewName("index");
		
		List<Category> categories = productClient.getMenu();
		List<Product> products = new ArrayList<Product>();
		for(Category c : categories){
			products.addAll(c.getProducts());
		}

		int firstProductId = products.get(0).getId();
		
		mv.addObject("categories", categories);

		UserDetails currentUser = (UserDetails) authentication.getPrincipal();
		mv.addObject("currentUser",currentUser);

		int waitingBevN=0, waitingTime=0;
		List<Order> orders = orderClient.getNotServedYet();

		for(Order order : orders) {
			for(Detail detail : order.getDetails()) {
				int productIdx = detail.getProductId()-firstProductId;
				waitingTime+=(products.get(productIdx).getSeconds()*detail.getQuantity());
				waitingBevN+=detail.getQuantity();
			}
		}
		
		List<Order> recent = orderClient.recent(currentUser.getUsername());

		for(Order order : recent) {
			for(Detail detail : order.getDetails()) {
				detail.setProduct(products.get(detail.getProductId()-firstProductId));
			}
		}
		mv.addObject("recent", recent);
		mv.addObject("waitingBevN",waitingBevN);
		mv.addObject("waitingTime",waitingTime);
		mv.addObject("jwt",interceptor.getToken().getValue());
		mv.addObject("orderurl", env.getProperty("feign.order-api.url"));

		return mv;
	}
	
	@GetMapping(path="/mypage")
	public ModelAndView mypage(Authentication authentication, ModelAndView mv) {
		mv.setViewName("mypage");
		Account currentUser = (Account) authentication.getPrincipal();
		List<Order> orders = orderClient.user(currentUser.getId());
		List<Product> products = productClient.all();
		int firstProductId = products.get(0).getId();

		for(Order order : orders) {
			for(Detail detail : order.getDetails()) {
				detail.setProduct(products.get(detail.getProductId()-firstProductId));
			}
		}
		mv.addObject("currentUser", currentUser);
		mv.addObject("orders", orders);
		
		return mv;
	}
	
	@GetMapping("/admin/management")
	public ModelAndView admin(ModelAndView mv, Authentication authentication) {
		mv.setViewName("admin");
		List<Product> products = productClient.all();
		int firstProductId = products.get(0).getId();
		List<Order> orders = orderClient.getNotServedYet();
		for(Order order : orders) {
			for(Detail detail : order.getDetails()) {
				detail.setProduct(products.get(detail.getProductId()-firstProductId));
			}
		}
		
		mv.addObject("orders",orders);
		mv.addObject("orderurl", env.getProperty("feign.order-api.url"));

		UserDetails currentUser = (UserDetails) authentication.getPrincipal();
		currentUser = accountService.loadUserByUsername(currentUser.getUsername());
		mv.addObject("jwt",interceptor.getToken().getValue());

		return mv;
	}

	@GetMapping("/create")
    public Account create(){

        Account account = new Account();     
        account.setId("user10");
        account.setPassword("asdf");
        account.setName("유저10");
		accountService.save(account, "ROLE_USER");

        return (Account) accountService.loadUserByUsername(account.getId());
    }
}
