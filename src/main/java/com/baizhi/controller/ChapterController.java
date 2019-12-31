package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ChapterController
 * @Discription
 * @Acthor
 * @Date 2019/12/19  19:26
 */
@Controller
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/showAllChapter")
    @ResponseBody
    public Map<String, Object> showAllChapter(Integer page, Integer rows, String album_id) {
        Map<String, Object> maps = new HashMap<>();
        //添加将要展示的结果集合
        List<Chapter> chapters = chapterService.showAllChapter(album_id, page, rows);
        if (chapters.size() == 0 && page > 1) {
            page--;
            chapters = chapterService.showAllChapter(album_id, page, rows);
        }
        maps.put("rows", chapters);
        //添加将要展示的页码
        maps.put("page", page);
        //添加总页数
        Integer records = chapterService.getChapterCount(album_id);
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        maps.put("total", total);
        //添加总条数
        maps.put("records", records);
        return maps;
    }

    @RequestMapping("/editChapter")
    @ResponseBody
    public Map<String, Object> editChapter(String oper, Chapter chapter, String album_id) {
        System.out.println(chapter);
        if (oper.equals("add")) {
            chapter.setChapter_id(new Date().getTime() + "");
            chapter.setChapter_create_date(new Date());
            chapter.setAlbum_id(album_id);
            chapterService.addChapter(chapter);
            Album album = new Album();
            albumService.editChapterCount(album_id);
        }
        if (oper.equals("edit")) {
            if (chapter.getChapter_cover().equals("")) {
                chapter.setChapter_cover(null);
            }
            chapterService.editChapter(chapter);
        }
        Map<String, Object> status = new HashMap<>();
        status.put("status", true);
        status.put("message", chapter.getChapter_id());
        return status;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public void upload(String chapter_id, MultipartFile chapter_cover, HttpSession session) {
        Encoder encoder = new Encoder();
        long ls = 0;
        MultimediaInfo m;
        String realPath = session.getServletContext().getRealPath("/album/music");
        String originalFilename = chapter_cover.getOriginalFilename();
        System.out.println("originalFilename:" + originalFilename);
        Chapter chapter = new Chapter();
        try {
            File file = new File(realPath + "/" + originalFilename);
            File chapter_cover1 = convert(chapter_cover);
            m = encoder.getInfo(chapter_cover1);
            ls = m.getDuration() / 1000;    //得到一个long类型的时长
            chapter_cover.transferTo(new File(realPath + "/" + originalFilename));
            //获取文件大小
            //file.length可以获取文件字节大小，保留两位小数
            double size = file.length() / 1024.0 / 1024.0;
            size = (double) Math.round(size * 100) / 100;
            chapter.setChapter_id(chapter_id);
            chapter.setChapter_cover(originalFilename);
            chapter.setChapter_size(size + "MB");//大小
            chapter.setChapter_duration(String.valueOf(ls / 60) + ":" + String.valueOf(ls % 60)); //转换为分钟：秒
            chapterService.editChapter(chapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
