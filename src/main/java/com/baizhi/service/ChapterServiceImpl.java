package com.baizhi.service;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ChapterServiceImpl
 * @Discription
 * @Acthor
 * @Date 2019/12/19  15:36
 */
@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterDao chapterDao;

    @Override
    public List<Chapter> showAllChapter(String album_id, Integer pageNum, Integer size) {
        Chapter chapter = new Chapter();
        chapter.setAlbum_id(album_id);
        RowBounds row = new RowBounds((pageNum - 1) * size, size);
        List<Chapter> chapters = chapterDao.selectByRowBounds(chapter, row);
        return chapters;
    }

    @Override
    public Integer getChapterCount(String album_id) {
        Chapter chapter = new Chapter();
        chapter.setAlbum_id(album_id);
        return chapterDao.selectCount(chapter);
    }

    @Override
    public void addChapter(Chapter chapter) {
        chapterDao.insert(chapter);
    }

    @Override
    public void editChapter(Chapter chapter) {
        System.out.println(chapter);
        int i = chapterDao.updateByPrimaryKeySelective(chapter);
        System.out.println("修改了" + i + "个对象");
    }

    @Override
    public void delChapter(Chapter chapter) {
        chapterDao.delete(chapter);
    }
}
