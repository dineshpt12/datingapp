package com.dating.datingsuggestions.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dating.datingsuggestions.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
