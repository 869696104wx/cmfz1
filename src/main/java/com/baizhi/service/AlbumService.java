package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {
    //展示所有专辑
    public List<Album> showAllAlbum(Integer pageNum, Integer size);

    //获取专辑总条数
    public Integer getAlbumCount();

    //添加专辑
    public void addAlbum(Album album);

    //修改专辑
    public void editAlbum(Album album);

    //修改专辑内章节个数
    public void editChapterCount(String album_id);
}
