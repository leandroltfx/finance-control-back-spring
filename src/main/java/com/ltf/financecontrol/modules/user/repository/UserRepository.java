package com.ltf.financecontrol.modules.user.repository;

import com.ltf.financecontrol.modules.user.model.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
