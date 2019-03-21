package com.rk.app.dataFetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rk.app.models.User;
import com.rk.app.services.UserService;

@Component
public class UserDataFetcher implements DataFetcher<User> {

    private final UserService userService;

    @Autowired
    UserDataFetcher(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User get(DataFetchingEnvironment env) {
        String userId = env.getArgument("id");
        final ObjectId id = new ObjectId(userId);
        return userService.findOneById(id);
    }
}
