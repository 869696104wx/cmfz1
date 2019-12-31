package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName AlbumController
 * @Discription
 * @Acthor
 * @Date 2019/12/19  15:53
 */
@Controller
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/showAllAlbum")
    @ResponseBody
    public Map<String, Object> showAllAlbum(Integer page, Integer rows) {
        Map<String, Object> maps = new HashMap<>();
        //添加将要展示的结果集合   rows
        List<Album> albums = albumService.showAllAlbum(page, rows);
        if (albums.size() == 0 && page > 1) {
            page--;
            albums = albumService.showAllAlbum(page, rows);
        }
        maps.put("rows", albums);
        //添加将要展示的页码 page
        maps.put("page", page);
        //添加总页数 total
        Integer records = albumService.getAlbumCount();
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        maps.put("total", total);
        //添加总条数 records
        maps.put("records", records);
        return maps;
    }

    @RequestMapping("/editAlbum")
    @ResponseBody
    public Map<String, Object> editAlbum(String oper, Album album) {
        if (oper.equals("add")) {
            album.setAlbum_id(UUID.randomUUID().toString());
            album.setAlbum_create_date(new Date());
            album.setAlbum_count(0);
            albumService.addAlbum(album);
        }
        if (oper.equals("edit")) {
            if (album.getAlbum_cover().equals("")) album.setAlbum_cover(null);
            albumService.editAlbum(album);
        }
        Map<String, Object> status = new HashMap<>();
        status.put("status", true);
        status.put("message", album.getAlbum_id());
        return status;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public void upload(String album_id, MultipartFile album_cover, HttpServletRequest request) throws IOException {
        // 文件上传
        if (album_cover != null) {
            ServletContext context = request.getServletContext();
            String realPath = context.getRealPath("img");
            String originalFilename = album_cover.getOriginalFilename();
            if (!originalFilename.equals("")) {
                album_cover.transferTo(new File(realPath + "/" + originalFilename));
            }
            Album album = new Album();
            album.setAlbum_id(album_id);
            album.setAlbum_cover(album_cover.getOriginalFilename());
            albumService.editAlbum(album);
        }
    }
}
