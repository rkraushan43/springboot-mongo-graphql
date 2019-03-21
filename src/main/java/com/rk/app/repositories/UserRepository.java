package com.rk.app.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rk.app.models.User;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, ObjectId> {

    Page<User> findByIdIn(List<String> ids, Pageable pageRequest);
}
