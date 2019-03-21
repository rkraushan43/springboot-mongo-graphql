package com.rk.app.services;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;

import com.rk.app.models.User;

import java.util.List;

public interface UserService {

    Page<User> findAllUsers(Integer first, Integer offset);

    User findOneById(ObjectId id);

    Page<User> findByIdIn(List<String> ids, Integer first, Integer offset);
}
