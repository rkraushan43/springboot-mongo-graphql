package com.rk.app.dataFetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rk.app.models.Article;
import com.rk.app.models.User;
import com.rk.app.services.ArticleService;

import java.util.List;

@Component
public class ArticlesDataFetcher implements DataFetcher<List<Article>> {

    private final ArticleService articleService;

    @Autowired
    ArticlesDataFetcher(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public List<Article> get(DataFetchingEnvironment env) {
        User user = env.getSource();
        final Integer first = env.getArgument("first");
        final Integer offset = env.getArgument("offset");

        List<String> articleIds = user.getArticlesIds();
        return articleService.findAllUserArticles(articleIds, first, offset);
    }
}
