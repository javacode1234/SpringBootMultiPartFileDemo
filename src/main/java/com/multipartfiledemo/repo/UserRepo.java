package com.multipartfiledemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multipartfiledemo.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

}
