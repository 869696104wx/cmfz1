package com.baizhi.service.cmfz;

import com.baizhi.entity.User;
import com.baizhi.entity.cmfz.AlbumAndArticle;
import com.baizhi.entity.cmfz.Cmfz_Banner;

import java.util.List;
import java.util.Map;

public interface CmfzService {
    //展示所有AlbumAndArticle
    public Map<String, List<AlbumAndArticle>> showAllAlbumAndArticle(String u_id);

    //展示所有Cmfz_Banner
    public List<Cmfz_Banner> showAllCmfz_Banner();

    //获取wen
    public Map<String, Object> getWen(String id);

    //登陆方法
    public Map<String, Object> login(String phone, String password);

    //注册方法
    public void register(String phone);

    //注册方法
    public List<User> getUser(String uid);

    //查询好友
    public List<Map<String, Object>> getFriendsByU_id(String uid);
}
