package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.Map;

public interface ArticleService {
    //展示文章
    public Map<String, Object> showAllArticle(Integer page, Integer rows);

    //添加文章
    public void insertArticle(Article article);

    //修改文章
    public void updateArticle(Article article);

    //删除文章
    public void deleteArticle(Article article);
}