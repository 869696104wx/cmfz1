package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.List;

public interface ChapterService {
    //展示全部章节
    public List<Chapter> showAllChapter(String album_id, Integer pageNum, Integer size);

    //获取章节个数
    public Integer getChapterCount(String album_id);

    //添加章节
    public void addChapter(Chapter chapter);

    //修改章节
    public void editChapter(Chapter chapter);

    //删除章节
    public void delChapter(Chapter chapter);
}
