package com.example.api;

import com.example.model.User;
import com.example.service.RoleService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserApiController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/api/user")
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList = userService.getAll(); // Assuming Product is the class representin
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping("/api/user/new")
    public ResponseEntity<?> create(@RequestBody User user) {
    	User userCreate = userService.add(user);
        if (userCreate == null) {
            return new ResponseEntity<>("Email đã được sử dụng.", HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(userService.add(user), HttpStatus.CREATED);

    }


    @PutMapping("/api/user/edit")
    public ResponseEntity<User> edit(@RequestBody User user) {

        return new ResponseEntity<>(userService.update(user), HttpStatus.CREATED);
    }


    @DeleteMapping("/api/user/delete")
    public ResponseEntity<String> delete(@RequestParam("id") int id) {
        userService.delete(id);
        return new ResponseEntity<>("Delete OK", HttpStatus.OK);
    }

    @GetMapping("/api/user/find")
    public ResponseEntity<User> findById(@RequestParam("id") int id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }
}
