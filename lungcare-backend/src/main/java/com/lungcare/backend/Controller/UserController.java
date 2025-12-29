package com.lungcare.backend.Controller;

import com.lungcare.backend.DTO.ApiResponse;
import com.lungcare.backend.DTO.UserRequestDTO;
import com.lungcare.backend.Entity.User;
import com.lungcare.backend.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ApiResponse<User> registerUser(@Valid @RequestBody UserRequestDTO dto)
    {
        User user = userService.createUser(dto);
        return new ApiResponse<>(true,"User register successfully",user);
    }

    @GetMapping
    public ApiResponse<List<User>> getAllUsers()
    {
        List<User> users = userService.getAllUsers();
        return new ApiResponse<>(true,"User retrived successfully",users);
    }

    @GetMapping("/{id}")
    public  ApiResponse<User> getUserById(@PathVariable Long id)
    {
        User user = userService.getUserById(id);
        return new ApiResponse<>(true,"User found",user);
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(@PathVariable Long id,@Valid @RequestBody UserRequestDTO dto)
    {
        User user = userService.updateUser(id,dto);
        return new ApiResponse<>(true,"User Updated Successfully",user);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<User> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return new ApiResponse<>(true,"User deleted Sccuessdully",null);
    }
}
