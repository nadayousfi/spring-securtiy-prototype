package com.example.demo.manette;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.entite.AuthRequest;
import com.example.demo.entite.UserInfo;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
private final UserInfoService userInfoService;
private AuthenticationManager authenticationManager;
private final JwtService jwtService;
@GetMapping("/welcome")
public ResponseEntity<String> welcome(){
	return ResponseEntity.ok("welcome to secure app");
}
@GetMapping("/users")
@PreAuthorize("hasAuthority('ADMIN_Roles')")
public ResponseEntity<List<UserInfo>> getAllUsers(){
	return ResponseEntity.ok(userInfoService.getAllUsers());
	
}

@PostMapping("signup")
public ResponseEntity<UserInfo> addOneUser(@RequestBody UserInfo userInfo){
	return ResponseEntity.ok(userInfoService.addUser(userInfo));
}
@PostMapping("login")
public ResponseEntity<String> login(@RequestBody AuthRequest authRequest){
	try {
		Authentication authenticate=authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if(authenticate.isAuthenticated()) {
			return ResponseEntity.ok(jwtService.generateToken(authRequest.getUsername()));
		}else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unknow parameter");
	}
		catch (UsernameNotFoundException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unknow user");
				
	}
}
@GetMapping("/users/{id}")
public ResponseEntity<UserInfo> getUser(@PathVariable Integer id){
	UserInfo info=userInfoService.getUser(id);
	if(info!=null) {
		return ResponseEntity.ok(info);
	}
	else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}
@GetMapping("users/{nom}")
public ResponseEntity<UserInfo> getOneUser(@PathVariable String nom){
	UserInfo info=userInfoService.getOneUser(nom);
	if(info!=null) {
		return ResponseEntity.ok(info);
	}
	else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}
}
