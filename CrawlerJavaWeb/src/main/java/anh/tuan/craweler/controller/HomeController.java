package anh.tuan.craweler.controller;

import anh.tuan.craweler.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class HomeController {
    @Autowired
    public ArticleService articleService;

    @GetMapping("/")
    public String fistPage(Model model) {
        model.addAttribute("articles", new ArrayList<>(articleService.getAllArticle(0)));
        model.addAttribute("NextPage", 1);
        return "article";
    }

    @GetMapping("/{page}")
    public String showArticle(Model model, @PathVariable("page") int page) {
        model.addAttribute("articles", new ArrayList<>(articleService.getAllArticle(page)));
        if (page > 0) model.addAttribute("prePage", page - 1);
        model.addAttribute("NextPage", page + 1);

        return "article";
    }

    @GetMapping("/insertNews")
    public String insertNews(Model model) {

        if (!articleService.insertArticleFormUrl())
            model.addAttribute("message", "Them cu lieu that bai");

        model.addAttribute("message", "them du lieu thanh cong ");
        return "result";
    }
}
