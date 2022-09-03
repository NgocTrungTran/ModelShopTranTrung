package com.tnt.modelshoptrantrung.controller;

import com.tnt.modelshoptrantrung.exception.AttributesExistsException;
import com.tnt.modelshoptrantrung.model.User;
import com.tnt.modelshoptrantrung.service.account.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/user/{username}")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/dashboards")
    public ModelAndView showUserPage(@PathVariable("username") String username){
        ModelAndView modelAndView = new ModelAndView( "/user/dashboards" );
        ModelAndView modelAndView1 = new ModelAndView ("/layout/header_body");
        ModelAndView modelAndView2 = new ModelAndView ("/layout/left_bar");

        Optional<User> userOptional = userService.findByUsername ( username );

        if ( !userOptional.isPresent () ) {
            throw new AttributesExistsException ( "Username not exist!" );
        }
        User user = userOptional.get ();
        modelAndView.addObject ( "admin", user.toUserDTO () );
        modelAndView1.addObject ( "admin", user.toUserDTO () );
        modelAndView2.addObject ( "admin", user.toUserDTO () );
        return modelAndView;
    }

}
