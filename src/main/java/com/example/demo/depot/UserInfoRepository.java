package com.example.demo.depot;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entite.UserInfo;
import java.util.List;


public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
Optional<UserInfo> findByName(String name);

}
