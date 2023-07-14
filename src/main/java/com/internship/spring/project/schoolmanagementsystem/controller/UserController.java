package com.internship.spring.project.schoolmanagementsystem.controller;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.PageDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.UserDTO;
import com.internship.spring.project.schoolmanagementsystem.repository.specification.SearchQuery;
import com.internship.spring.project.schoolmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO, @RequestParam String role){
        return ResponseEntity.ok(userService.createUser(userDTO,role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUser(userDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/list/{role}")
    public ResponseEntity<Page<UserDTO>> getUsersByRole(@PathVariable String role, PageDTO pageDTO){
        return ResponseEntity.ok(userService.findUserByRole(role,pageDTO));
    }

    @PostMapping("/list")
    public ResponseEntity<Page<UserDTO>> filterUsers (@RequestBody List<SearchQuery> filters, PageDTO pageDTO) {
        return ResponseEntity.ok(userService.filterUsers(filters,pageDTO));
    }


}
