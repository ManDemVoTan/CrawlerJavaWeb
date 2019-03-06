package anh.tuan.craweler.services;

import anh.tuan.craweler.model.Article;

import java.util.List;


public interface ArticleService {
    boolean insertArticleFormUrl();
    List<Article> getAllArticle(int page);
}
