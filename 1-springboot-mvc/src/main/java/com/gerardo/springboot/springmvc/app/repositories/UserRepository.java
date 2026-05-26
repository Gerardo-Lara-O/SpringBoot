package com.gerardo.springboot.springmvc.app.repositories;

import com.gerardo.springboot.springmvc.app.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}
