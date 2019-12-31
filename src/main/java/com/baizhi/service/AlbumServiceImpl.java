package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AlbunServiceImpl
 * @Discription
 * @Acthor
 * @Date 2019/12/19  15:26
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;

    @Override
    public List<Album> showAllAlbum(Integer pageNum, Integer size) {
        Album album = new Album();
        //offset:起始的下标
        //limit:每页展示条数
        RowBounds row = new RowBounds((pageNum - 1) * size, size);
        List<Album> albums = albumDao.selectByRowBounds(album, row);
        return albums;
    }

    @Override
    public Integer getAlbumCount() {
        return albumDao.selectCount(new Album());
    }

    @Override
    public void addAlbum(Album album) {
        albumDao.insert(album);
    }

    @Override
    public void editAlbum(Album album) {
        albumDao.updateByPrimaryKeySelective(album);
    }

    @Override
    public void editChapterCount(String album_id) {
        Album album = new Album();
        album.setAlbum_id(album_id);
        Album a = albumDao.selectOne(album);
        a.setAlbum_count(a.getAlbum_count() + 1);
        albumDao.updateByPrimaryKeySelective(a);
    }

}
