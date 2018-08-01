package com.hotfix.jpa.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotfix.jpa.enitity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{ 

}