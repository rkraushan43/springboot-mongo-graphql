package com.rk.app.services;

import java.util.List;

import com.rk.app.models.Article;

public interface ArticleService {

    List<Article> findAllUserArticles(List<String> userId, Integer first, Integer offset);
}
