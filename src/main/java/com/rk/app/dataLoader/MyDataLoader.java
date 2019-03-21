package com.rk.app.dataLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rk.app.models.Article;
import com.rk.app.models.User;
import com.rk.app.repositories.ArticleRepository;
import com.rk.app.repositories.UserRepository;

@Component
public class MyDataLoader {

	private final UserRepository	userRepository;
	private final ArticleRepository	articleRepository;

	@Autowired
	public MyDataLoader(UserRepository userRepository, ArticleRepository articleRepository) {

		this.userRepository = userRepository;
		this.articleRepository = articleRepository;
	}

	@PostConstruct
	private void generateData() {

		List<User> users = new ArrayList<>();
		users.add(User.builder().name("Raushan").createdAt(new Date()).age(24).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
		users.add(User.builder().name("Aman").createdAt(new Date()).age(24).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
		users.add(User.builder().name("Keshav").createdAt(new Date()).age(53).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
		users.add(User.builder().name("Rakesh").createdAt(new Date()).age(14).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
		users = (ArrayList<User>) userRepository.saveAll(users);
		List<Article> articles = new ArrayList<>();
		articles.add(Article.builder().title("Java 8 Lambdas").minutesRead(8).authorId(users.get(0).getId().toString()).build());
		articles.add(Article.builder().title("GraphQL Getting Started").minutesRead(10).authorId(users.get(1).getId().toString()).build());
		articles.add(Article.builder().title("Spring Boot + WebSockets").minutesRead(6).authorId(users.get(3).getId().toString()).build());
		articles = (ArrayList<Article>) articleRepository.saveAll(articles);
		User me = users.get(0);
		users.get(0).setArticlesIds(Arrays.asList(articles.get(0).getId().toHexString()));
		users.get(0).setArticlesIds(Arrays.asList(articles.get(1).getId().toHexString()));
		users.get(1).setArticlesIds(Arrays.asList(articles.get(2).getId().toHexString()));
		users.get(3).setArticlesIds(Arrays.asList(articles.get(0).getId().toHexString()));
		userRepository.saveAll(users);
		List<String> myFriendsIds = new ArrayList<>();
		for (int i = 1; i < users.size(); i++) {
			myFriendsIds.add(users.get(i).getId().toHexString());
		}
		me.setFriendsIds(myFriendsIds);
		userRepository.save(me);
	}
}
