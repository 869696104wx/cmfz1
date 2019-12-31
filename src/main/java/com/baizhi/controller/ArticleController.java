package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ArticleController
 * @Discription
 * @Acthor
 * @Date 2019/12/22  16:41
 */
@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("showAllArticle")
    @ResponseBody
    public Map<String, Object> showAllArticle(Integer page, Integer rows) {
        return articleService.showAllArticle(page, rows);
    }

    @RequestMapping("editArticle")
    @ResponseBody
    public Map<String, Object> edit(String oper, Article article) {
        Map<String, Object> map = new HashMap<>();
        if (oper.equals("add")) {
            articleService.insertArticle(article);
        }
        if (oper.equals("edit")) {
            articleService.updateArticle(article);
        }
        if (oper.equals("del")) {
            articleService.deleteArticle(article);
        }
        return map;
    }

}
