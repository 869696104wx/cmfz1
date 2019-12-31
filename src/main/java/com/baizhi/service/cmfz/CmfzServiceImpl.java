package com.baizhi.service.cmfz;

import com.baizhi.dao.*;
import com.baizhi.entity.*;
import com.baizhi.entity.cmfz.AlbumAndArticle;
import com.baizhi.entity.cmfz.Cmfz_Banner;
import com.baizhi.entity.cmfz.Cmfz_Chapter;
import com.baizhi.entity.cmfz.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CmfzServiceImpl
 * @Discription
 * @Acthor
 * @Date 2019/12/26  16:46
 */
@Service
public class CmfzServiceImpl implements CmfzService {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private FriendDao friendDao;

    @Override
    public Map<String, List<AlbumAndArticle>> showAllAlbumAndArticle(String u_id) {
        Map<String, List<AlbumAndArticle>> map = new HashMap<>();
        List<AlbumAndArticle> wens = new ArrayList<>();
        List<AlbumAndArticle> ssyj = new ArrayList<>();
        List<AlbumAndArticle> xmfy = new ArrayList<>();
        User user = new User();
        user.setU_id(u_id);
        List<User> select = userDao.select(user);
        //添加    0 —— wen —— 专辑 —— 吉祥妙音
        List<Album> albums = albumDao.selectAll();
        for (Album a : albums) {
            AlbumAndArticle aaa = new AlbumAndArticle();
            aaa.setThumbnail(a.getAlbum_cover());
            aaa.setTitle(a.getAlbum_title());
            aaa.setAuthor(a.getAlbum_author());
            aaa.setType(0);
            aaa.setSet_count(a.getAlbum_count());
            aaa.setCreate_date(a.getAlbum_create_date());
            wens.add(aaa);
        }

        //添加    1 —— si —— 文章 —— 甘露妙宝
        List<Article> articles = articleDao.selectAll();
        for (Article a : articles) {
            AlbumAndArticle aaa = new AlbumAndArticle();
            aaa.setTitle(a.getA_title());
            aaa.setAuthor(a.getA_author());
            aaa.setType(1);
            aaa.setCreate_date(a.getA_create_date());
            if (aaa.getAuthor().equals(select.get(0).getM_name())) {
                ssyj.add(aaa);
            } else {
                xmfy.add(aaa);
            }

        }
        map.put("wens", wens);
        map.put("ssyj", ssyj);
        map.put("xmfy", xmfy);
        return map;
    }

    @Override
    public List<Cmfz_Banner> showAllCmfz_Banner() {
        List<Banner> banners = bannerDao.selectAll();
        List<Cmfz_Banner> list = new ArrayList<>();
        for (Banner b : banners) {
            Cmfz_Banner cmfz_banner = new Cmfz_Banner();
            cmfz_banner.setThumbnail("http://" + b.getB_cover());
            cmfz_banner.setDesc(b.getB_describe());
            cmfz_banner.setId(b.getB_id());
            list.add(cmfz_banner);
        }
        return list;
    }

    @Override
    public Map<String, Object> getWen(String id) {
        //获取id下的Album对象
        Album a = new Album();
        a.setAlbum_id(id);
        List<Album> select = albumDao.select(a);
        Album album = select.get(0);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> introduction = new HashMap<>();
        introduction.put("thumbnail", "http://" + album.getAlbum_cover());//缩略图
        introduction.put("title", album.getAlbum_title());//专辑名
        introduction.put("score", album.getAlbum_score());//分数（0-5）
        introduction.put("author", album.getAlbum_author());//作者
        introduction.put("broadcast", album.getAlbum_beam());//播音
        introduction.put("set_count", album.getAlbum_count());//集数
        introduction.put("brief", album.getAlbum_intro());//内容简介
        introduction.put("create_date", album.getAlbum_create_date());//发布日期
        map.put("introduction", introduction);
        Chapter chapter = new Chapter();
        chapter.setAlbum_id(id);
        List<Chapter> chapters = chapterDao.select(chapter);
        List<Cmfz_Chapter> list = new ArrayList<>();
        int i = 1;
        for (Chapter c : chapters) {
            Cmfz_Chapter cc = new Cmfz_Chapter();
            cc.setTitle("第" + i + "集");
            cc.setDownload_url(c.getChapter_cover());
            cc.setSize(c.getChapter_size());
            cc.setDuration(c.getChapter_duration());
            i++;
        }
        map.put("list", list);
        return map;
    }

    @Override
    public Map<String, Object> login(String phone, String password) {
        User u = new User();
        u.setU_phone(phone);
        u.setU_password(password);
        List<User> users = userDao.select(u);
        if (users.size() == 0) {
            throw new RuntimeException("号码或密码错误");
        }
        User user = users.get(0);
        HashMap<String, Object> map = new HashMap<>();
        map.put("password", password);//密码
        map.put("farmington", user.getU_dharma());//法名
        map.put("uid", user.getU_id());//用户id
        map.put("nickname", user.getU_username());//昵称
        map.put("gender", user.getU_sex());//性别
        map.put("photo", "http://" + user.getU_photo());//头像
        map.put("location", user.getU_province() + user.getU_city());//所在地
        map.put("province", user.getU_province());//省市
        map.put("city", user.getU_city());//地区
        map.put("description", user.getU_sign());//个人签名
        map.put("phone", phone);
        return map;
    }

    @Override
    public void register(String phone) {
        User u = new User();
        u.setU_phone(phone);
        List<User> users = userDao.select(u);
        if (users.size() != 0) {
            throw new RuntimeException("该号码已被注册");
        }
    }

    @Override
    public List<User> getUser(String uid) {
        User user = new User();
        user.setU_id(uid);
        List<User> list = userDao.select(user);
        if (list.size() == 0) {
            throw new RuntimeException("用户名不存在");
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getFriendsByU_id(String uid) {
        List<Map<String, Object>> users = new ArrayList<>();
        Friend friend = new Friend();
        friend.setU_id1(uid);
        List<Friend> friends = friendDao.select(friend);
        for (Friend f : friends) {
            User user = new User();
            user.setU_id(f.getU_id2());
            List<User> select = userDao.select(user);
            Map<String, Object> map = new HashMap<>();
            map.put("password", select.get(0).getU_password());//密码
            map.put("farmington", select.get(0).getU_dharma());//法名
            map.put("uid", select.get(0).getU_id());//用户id
            map.put("nickname", select.get(0).getU_username());//昵称
            map.put("gender", select.get(0).getU_sex());//性别
            map.put("photo", "http://" + select.get(0).getU_photo());//头像
            map.put("location", select.get(0).getU_province() + select.get(0).getU_city());//所在地
            map.put("province", select.get(0).getU_province());//省市
            map.put("city", select.get(0).getU_city());//地区
            map.put("description", select.get(0).getU_sign());//个人签名
            map.put("phone", select.get(0).getU_phone());
            users.add(map);
        }
        return users;
    }


}
