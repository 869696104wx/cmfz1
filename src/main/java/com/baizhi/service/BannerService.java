package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;

public interface BannerService {
    //展示轮播图
    public List<Banner> showAllBanner(Integer pageNum, Integer size);

    //获取轮播图总条数
    public Integer getBannerCount();

    //添加轮播图
    public void insertBanner(Banner banner);

    //修改轮播图状态
    public void updateBanner(Banner banner);

    //删除轮播图
    public void deleteBanner(Banner banner);

}
