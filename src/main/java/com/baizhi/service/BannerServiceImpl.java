package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName BannerServiceImpl
 * @Discription
 * @Acthor
 * @Date 2019/12/18  14:54
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    public List<Banner> showAllBanner(Integer pageNum, Integer size) {
        Banner banner = new Banner();
        //offset:起始的下标
        //limit:每页展示条数
        RowBounds row = new RowBounds((pageNum - 1) * size, size);
        List<Banner> banners = bannerDao.selectByRowBounds(banner, row);
        return banners;
    }

    @Override
    public Integer getBannerCount() {
        List<Banner> banners = bannerDao.selectAll();
        return banners.size();
    }

    @Override
    public void insertBanner(Banner banner) {
        banner.setB_create_date(new Date());
        System.out.println(banner);
        bannerDao.insert(banner);
    }

    @Override
    public void updateBanner(Banner banner) {
        bannerDao.updateByPrimaryKeySelective(banner);
    }

    @Override
    public void deleteBanner(Banner banner) {
        bannerDao.delete(banner);
    }
}
