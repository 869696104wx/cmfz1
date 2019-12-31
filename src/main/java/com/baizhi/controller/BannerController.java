package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName BannerController
 * @Discription
 * @Acthor
 * @Date 2019/12/18  15:44
 */

@Controller
@RequestMapping("banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/showAllBanner")
    @ResponseBody
    public Map<String, Object> showAllBanner(Integer page, Integer rows) {
        /*if(page==null)page=1;
        if(rows==null)rows=3;*/
        Map<String, Object> maps = new HashMap<>();
        //添加将要展示的结果集合
        List<Banner> banners = bannerService.showAllBanner(page, rows);
        if (banners.size() == 0 && page > 1) {
            page--;
            banners = bannerService.showAllBanner(page, rows);
        }
        maps.put("rows", banners);
        //添加将要展示的页码
        maps.put("page", page);
        //添加总页数
        Integer records = bannerService.getBannerCount();
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        maps.put("total", total);
        //添加总条数
        maps.put("records", records);
        return maps;
    }

    @RequestMapping("/editBanner")
    @ResponseBody
    public Map<String, Object> editBanner(String oper, Banner banner) {
        if (oper.equals("add")) {
            banner.setB_id(UUID.randomUUID().toString());
            bannerService.insertBanner(banner);
        }
        if (oper.equals("edit")) {
            banner.setB_cover(null);
            bannerService.updateBanner(banner);
        }
        if (oper.equals("del")) {
            bannerService.deleteBanner(banner);
        }
        Map<String, Object> status = new HashMap<>();
        status.put("status", true);
        status.put("message", banner.getB_id());
        return status;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public void upload(String b_id, MultipartFile b_cover, HttpServletRequest request) throws IOException {
        // 文件上传
        if (b_cover != null) {
            ServletContext context = request.getServletContext();
            //获取servletcontext对象,为了调用servletcontext接口中的方法
            String realPath = context.getRealPath("img");//所在文件夹名
            String originalFilename = b_cover.getOriginalFilename();//文件名
            if (!originalFilename.equals("")) {
                b_cover.transferTo(new File(realPath + "/" + originalFilename));
            }
            Banner banner = new Banner();
            banner.setB_id(b_id);
            System.out.println(b_id);
            banner.setB_cover(b_cover.getOriginalFilename());
            bannerService.updateBanner(banner);
        }
    }

}
