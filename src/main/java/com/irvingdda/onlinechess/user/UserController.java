package com.irvingdda.onlinechess.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping(path="/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/add")
    public @ResponseBody String addUser( @RequestParam String nickName) {
        User user = new User();
        user.setNickName(nickName);
        service.saveUser(user);

        return "Saved";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getUsers() {
        return service.findAllUsers();
    }
    
    
}
