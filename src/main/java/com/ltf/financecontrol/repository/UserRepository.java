package com.ltf.financecontrol.repository;

import com.ltf.financecontrol.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
