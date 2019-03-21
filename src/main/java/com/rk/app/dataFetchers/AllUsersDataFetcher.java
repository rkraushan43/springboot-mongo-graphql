package com.rk.app.dataFetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.rk.app.models.User;
import com.rk.app.services.UserService;

@Component
public class AllUsersDataFetcher implements DataFetcher<Page<User>> {

    private final UserService userService;

    @Autowired
    public AllUsersDataFetcher(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Page<User> get(DataFetchingEnvironment env) {
        User user = env.getSource();
        final Integer first = env.getArgument("first");
        final Integer offset = env.getArgument("offset");

        return user == null ?
                userService.findAllUsers(first, offset) :
                userService.findByIdIn(user.getFriendsIds(), first, offset);
    }
}
