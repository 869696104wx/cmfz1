package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Discription
 * @Acthor
 * @Date 2019/12/22  10:33
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/showAllUser")
    @ResponseBody
    public Map<String, Object> showAllUser(Integer page, Integer rows) {
        Map<String, Object> maps = userService.showAllUser(page, rows);
        return maps;
    }

    @RequestMapping("/editUser")
    @ResponseBody
    public Map<String, Object> editUser(String oper, User user) {
        Map<String, Object> status = new HashMap<>();
        try {
            userService.editUser(user);
            status.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            status.put("status", false);
        }
        return status;
    }

    @RequestMapping("/userOut")
    public void userOut(HttpServletResponse response, HttpServletRequest request) throws IOException {
        List<User> users = userService.showAllUser();
        //此时对象中的图片仅有文件名，需要将其补全至全路径
        String realPath = request.getSession().getServletContext().getRealPath("/img");
        for (User u : users) {
            u.setU_photo(realPath + "//" + u.getU_photo());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("佛家子弟", "小师傅"),
                User.class, users);
        String encode = URLEncoder.encode("驰名法州.xls", "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + encode);
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/getAllCeeate")
    @ResponseBody
    public Map<String, List<Integer>> getAllCeeate() {
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("Man", userService.getMan());
        map.put("Woman", userService.getWoman());
        System.out.println(map);
        return map;
    }
}
