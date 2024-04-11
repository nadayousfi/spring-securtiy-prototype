package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.depot.UserInfoRepository;
import com.example.demo.entite.UserInfo;

import lombok.RequiredArgsConstructor;

@Service
public class UserInfoService implements UserDetailsService{
	@Autowired UserInfoRepository userInfoRepository;
@Autowired PasswordEncoder passwordEncoder;
@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo=userInfoRepository.findByName(username);
		return userInfo.map(UserInfoDetails::new)
				.orElseThrow(()-> new UsernameNotFoundException("theres no user name like this"+username));
	}
public UserInfo getOneUser(String name) {
	return userInfoRepository.findByName(name).orElse(null);
	
}
public UserInfo addUser(UserInfo userInfo) {
	userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
	return userInfoRepository.save(userInfo);
}
public List<UserInfo> getAllUsers(){
	return userInfoRepository.findAll();
}
public UserInfo getUser(Integer id) {
	return userInfoRepository.findById(id).orElse(null);
}
}
