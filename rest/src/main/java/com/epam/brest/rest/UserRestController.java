package com.epam.brest.rest;


import com.epam.brest.model.User;
import com.epam.brest.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Class UserRestController implements user requests mapping methods.
 */
@CrossOrigin
@RestController
public class UserRestController {

    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<Model> doLogin(@RequestParam final String user, @RequestParam final String password, final Model model)
    {
        LOGGER.debug("doLogin:Login:{}",user);
        User user1= userService.getUserByLogin(user);
        if(user!=null&&BCrypt.checkpw(password,user1.getUserPassword()))
        {
            model.addAttribute("success", true);
            model.addAttribute("msg", "User authenticated!");
        }
        else
        {
            {
                model.addAttribute("success", false);
                model.addAttribute("msg", "Incorrect user or password.");
            }
        }
        System.out.println(BCrypt.checkpw(password,user1.getUserPassword()));
        return new ResponseEntity<Model>(model, HttpStatus.OK);

    }
}
