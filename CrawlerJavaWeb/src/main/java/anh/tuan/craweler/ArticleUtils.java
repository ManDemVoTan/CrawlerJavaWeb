package anh.tuan.craweler;

import anh.tuan.craweler.model.Article;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ArticleUtils {
    public static List<Article> getArticleList(Document doc) {
        List<Article> articles = new ArrayList<>();
        Elements hrefs = doc.select("a[data-linktype=newsdetail]");
        for (Element aTag : hrefs) {
            Article article = new Article();
            article.setTitle(aTag.attr("title"));
            String href = aTag.attr("href");
            if (href.contains(PageConst.DAN_TRI_DOMAIN)) {
                article.setDetailUrl(href);
            } else {
                article.setDetailUrl(PageConst.DAN_TRI_FULL_URL + href);
            }
            articles.add(article);
        }
        return articles;
    }

    public static Article parse(Document doc, Article article) {
        Element divContent = doc.getElementById("divNewsContent");
        if (divContent != null) {
            article.setContent(divContent.text());
            Elements images = divContent.getElementsByTag("img");
            if (images != null) {
                Element image = images.first();
                if (image != null) {
                    article.setMainImageUrl(image.attr("src"));
                }
            }
        }


        return article;
    }

}
