package com.baizhi;


import com.baizhi.dao.AdminDao;
import com.baizhi.dao.BannerDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Banner;
import com.baizhi.entity.cmfz.AlbumAndArticle;
import com.baizhi.service.cmfz.CmfzService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Cmfz1ApplicationTests {

    @Autowired
    AdminDao adminDao;
    @Autowired
    BannerDao bannerDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CmfzService cmfzService;

    @Test
    public void test1() {
        List<Banner> banners = bannerDao.selectAll();
        banners.forEach(Banner -> System.out.println(Banner));
    }

    @Test
    public void test2() {
        Map<String, List<AlbumAndArticle>> map = cmfzService.showAllAlbumAndArticle("1");
        System.out.println(map.get("wens"));
        System.out.println(map.get("ssyj"));
        System.out.println(map.get("xmfy"));

    }

}
