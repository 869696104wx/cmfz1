package com.baizhi.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @ClassName KindeditorController
 * @Discription
 * @Acthor
 * @Date 2019/12/23  16:53
 */
@RestController
@RequestMapping("kindeditor")
public class KindeditorController {
    @RequestMapping("upload")
    public Map<String, Object> upload(MultipartFile img, HttpServletRequest request) throws UnknownHostException {
        Map<String, Object> map = new HashMap<>();
        String name = UUID.randomUUID().toString() + img.getOriginalFilename();
        try {
            img.transferTo(new File(request.getSession().getServletContext().getRealPath("/article/img"), name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("error", 0);
        String scheme = request.getScheme();//http
        InetAddress localHost = InetAddress.getLocalHost();//ip
        String s = localHost.toString();
        String ip = s.split("/")[1];
        int serverPort = request.getServerPort();//8989
        String contextPath = request.getContextPath();//    /cmfz1
        String url = scheme + "://" + ip + ":" + serverPort + contextPath + "/article/img/" + name;
        map.put("url", url);
        return map;
    }

    @RequestMapping("getAll")
    public Map<String, Object> getAll(HttpServletRequest request) throws UnknownHostException {
        /*{
	     "moveup_dir_path": "",
	     "current_dir_path": "",
	     "current_url": "http://localhost:8989/cmfz/article/img/",
	     "total_count": 5,
	"    file_list": [{
		"is_dir": false,
		"has_file": false,
		"filesize": 208736,
		"dir_path": "",
		"is_photo": true,
		"filetype": "jpg",
		"filename": "1241601537255682809.jpg",
		"datetime": "2018-06-06 00:36:39"
	}*/
        Map<String, Object> map = new HashMap<>();
        String realPath = request.getSession().getServletContext().getRealPath("/article/img");
        File files = new File(realPath);
        String[] names = files.list();
        List<Map<String, Object>> list = new ArrayList<>();
        for (String name : names) {
            Map<String, Object> file = new HashMap<>();
            file.put("is_dir", false);
            file.put("has_file", false);
            File file1 = new File(realPath, name);
            file.put("filesize", file1.length());
            file.put("dir_path", "");
            file.put("is_photo", true);
            file.put("filetype", FilenameUtils.getExtension(name));
            file.put("filename", name);
            file.put("datetime", "2019-01-01 00:00:01");
            list.add(file);
        }
        map.put("moveup_dir_path", "");
        map.put("current_dir_path", "");
        String scheme = request.getScheme();//http
        InetAddress localHost = InetAddress.getLocalHost();//ip
        String s = localHost.toString();
        String ip = s.split("/")[1];
        int serverPort = request.getServerPort();//8989
        String contextPath = request.getContextPath();//    /cmfz1
        String url = scheme + "://" + ip + ":" + serverPort + contextPath + "/article/img/";
        map.put("current_url", url);
        map.put("total_count", names.length);
        map.put("file_list", list);
        return map;
    }

    @RequestMapping("select")
    public void select(String content) {
        System.out.println(content);
    }
}
