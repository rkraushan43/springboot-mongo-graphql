package com.rk.app.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rk.app.models.Article;

import java.util.List;

public interface ArticleRepository extends PagingAndSortingRepository<Article, ObjectId> {

    List<Article> findByIdIn(List<String> ids, PageRequest pageRequest);
}
