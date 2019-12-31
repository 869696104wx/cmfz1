package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName ArticleServiceImpl
 * @Discription
 * @Acthor
 * @Date 2019/12/22  16:30
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public Map<String, Object> showAllArticle(Integer page, Integer rows) {
        Article article = new Article();
        //offset:起始的下标
        //limit:每页展示条数
        RowBounds row = new RowBounds((page - 1) * rows, rows);
        List<Article> users = articleDao.selectByRowBounds(article, row);
        if (users.size() == 0 && page > 1) {
            page--;
            row = new RowBounds((page - 1) * rows, rows);
            users = articleDao.selectByRowBounds(article, row);
        }
        Map<String, Object> maps = new HashMap<>();
        maps.put("rows", users);
        //添加将要展示的页码
        maps.put("page", page);
        //添加总页数
        Integer records = articleDao.selectCount(article);
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        maps.put("total", total);
        //添加总条数
        maps.put("records", records);
        return maps;
    }

    @Override
    public void insertArticle(Article article) {
        String id = UUID.randomUUID().toString();
        article.setA_id(id);
        article.setA_create_date(new Date());
        int i = articleDao.insertSelective(article);
        if (i == 0) throw new RuntimeException("添加失败");
    }

    @Override
    public void updateArticle(Article article) {
        int i = articleDao.updateByPrimaryKeySelective(article);
        if (i == 0) throw new RuntimeException("修改失败");
    }

    @Override
    public void deleteArticle(Article article) {
        int i = articleDao.delete(article);
        if (i == 0) throw new RuntimeException("删除失败");
    }
}
