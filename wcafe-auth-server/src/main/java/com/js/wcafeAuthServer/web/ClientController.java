package com.js.wcafeAuthServer.web;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

import javax.validation.Valid;

import com.js.wcafeAuthServer.constant.ClientType;
import com.js.wcafeAuthServer.dto.ClientDetailsImpl;
import com.js.wcafeAuthServer.dto.RegisterClientInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*
* 동적 클라이언트 등록에 사용되는 컨트롤러
*/
@Controller
@RequestMapping("/client")
public class ClientController {
    
    @Autowired private ClientRegistrationService clientRegistrationService;
    
    @GetMapping("/register")
    public ModelAndView registerPage(ModelAndView mav) {
        mav.setViewName("client/register");
        mav.addObject("registry", new RegisterClientInfo());
        return mav;
    }
    
    @GetMapping("/dashboard")
    public ModelAndView dashboard(ModelAndView mv) {
        mv.addObject("applications",
                clientRegistrationService.listClientDetails());
        return mv;
    }
    
    @PostMapping("/save")
    public ModelAndView save(@Valid RegisterClientInfo clientDetails,ModelAndView mav ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()) {
            return new ModelAndView("client/register");
        }
        
        ClientDetailsImpl client = new ClientDetailsImpl();
        client.addAdditionalInformation("name", clientDetails.getName());
        client.setRegisteredRedirectUri(new HashSet<>(Arrays.asList(clientDetails.getRedirectUri())));
        client.setClientType(ClientType.PUBLIC);
        client.setClientId(UUID.randomUUID().toString());
        client.setAuthorizedGrantTypes(Arrays.asList("authorization_code","password","client_credentials","implicit","refresh_token"));
        client.setClientSecret("{noop}"+UUID.randomUUID().toString());
        client.setAccessTokenValiditySeconds(3600);
        client.setScope(Arrays.asList("read","write"));
        clientRegistrationService.addClientDetails(client);
        
        mav.setViewName("redirect:/client/dashboard");
        
        return mav;
    }
    
    @GetMapping("/remove")
    public ModelAndView remove(
            @RequestParam(value = "client_id", required = false) String clientId) {
 
        clientRegistrationService.removeClientDetails(clientId);
        ModelAndView mv = new ModelAndView("redirect:/client/dashboard");
        mv.addObject("applications",
                clientRegistrationService.listClientDetails());
        return mv;
    }
}
