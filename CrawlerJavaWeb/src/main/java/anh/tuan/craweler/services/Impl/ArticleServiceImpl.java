package anh.tuan.craweler.services.Impl;

import anh.tuan.craweler.ArticleUtils;
import anh.tuan.craweler.PageConst;
import anh.tuan.craweler.model.Article;
import anh.tuan.craweler.repositories.ArticleRepository;
import anh.tuan.craweler.services.ArticleService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    public ArticleRepository articleRepository;
    @Override
    public boolean insertArticleFormUrl() {
        // model.addAttribute(articleService.getAllArticle());
        try {
            Document doc = Jsoup.connect(PageConst.DAN_TRI_FULL_URL).get();
            List<Article> articles = ArticleUtils.getArticleList(doc);
//            System.out.println("page:" + doc.title());
//            System.out.println("article count: " + articles.size());

            for (Article article : articles) {
                System.out.println("url: " + article.getDetailUrl());
                Document detailDoc = Jsoup.connect(article.getDetailUrl()).get();
                ArticleUtils.parse(detailDoc, article);
            }

           for (Article article : articles) {
//
////                System.out.println("title: " + article.getTitle());
////                System.out.println("url: " + article.getDetailUrl());
////                System.out.println("img: " + article.getMainImageUrl());
////                System.out.println("content: " + article.getContent());
               System.out.println( articleRepository.save(article).getId());
            }

             return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Article> getAllArticle(int page) {
        List<Article> articles = new ArrayList<>();

        articleRepository.findAll( PageRequest.of(page,10)).forEach(articles::add);
        return articles;
    }
}
