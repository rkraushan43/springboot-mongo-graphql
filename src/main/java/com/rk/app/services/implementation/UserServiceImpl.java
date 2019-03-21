package com.rk.app.services.implementation;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rk.app.models.User;
import com.rk.app.repositories.UserRepository;
import com.rk.app.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final int		DEFAULT_FIRST	= 5;
	private static final int		DEFAULT_PAGE	= 0;
	private final UserRepository	userRepository;

	@Autowired
	UserServiceImpl(UserRepository userRepository) {

		this.userRepository = userRepository;
	}

	@Override
	public Page<User> findAllUsers(Integer first, Integer offset) {

		final Integer size = Optional.ofNullable(first).orElse(DEFAULT_FIRST);
		final Integer page = Optional.ofNullable(offset).orElse(DEFAULT_PAGE);

		final PageRequest pageable = new PageRequest(page, size);
		return userRepository.findAll(pageable);
	}

	@Override
	public User findOneById(ObjectId id) {

		Optional<User> user = userRepository.findById(id);
		return user.isPresent() ? user.get() : null;
	}

	@Override
	public Page<User> findByIdIn(List<String> ids, Integer first, Integer offset) {

		final Integer size = Optional.ofNullable(first).orElse(DEFAULT_FIRST);
		final Integer page = Optional.ofNullable(offset).orElse(DEFAULT_PAGE);

		final PageRequest pageRequest = new PageRequest(page, size);
		return userRepository.findByIdIn(ids, pageRequest);
	}
}
