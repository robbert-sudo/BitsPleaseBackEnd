package bitspleaseApp.controller;

import bitspleaseApp.dto.response.UserDetailsResponse;
import bitspleaseApp.service.AdminService;
import bitspleaseApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/deletedusers")
    public ResponseEntity getDisabledUsers() {
        Set<UserDetailsResponse> userDetailsResponse = adminService.findAllByDisabled();
        return ResponseEntity.ok(userDetailsResponse);
    }


   @DeleteMapping(value = "delete/{username}")
    public ResponseEntity deleteUser(@PathVariable("username") String username) {
        adminService.delete(username);
        return ResponseEntity.ok("User verwijderd!");
   }


}
