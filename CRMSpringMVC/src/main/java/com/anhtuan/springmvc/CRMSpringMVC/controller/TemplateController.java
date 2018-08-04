package com.anhtuan.springmvc.CRMSpringMVC.controller;

import com.anhtuan.springmvc.CRMSpringMVC.dao.login.UserJpaRepository;
import com.anhtuan.springmvc.CRMSpringMVC.model.login.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class TemplateController {

    private final UserJpaRepository userJpaRepository;

    private final AuthenticationTrustResolver authenticationTrustResolver;

    private final PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    public TemplateController(UserJpaRepository userJpaRepository, AuthenticationTrustResolver authenticationTrustResolver, PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices) {
        this.userJpaRepository = userJpaRepository;
        this.authenticationTrustResolver = authenticationTrustResolver;
        this.persistentTokenBasedRememberMeServices = persistentTokenBasedRememberMeServices;
    }

    @GetMapping(value = "/")
    public String home(ModelMap modelMap) {

        List<User> list = userJpaRepository.findAll();
        modelMap.addAttribute("list", list);
        return "home";
    }

    @GetMapping(value = "/security/asd")
    public String security() {
        return "security";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/";
    }

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
