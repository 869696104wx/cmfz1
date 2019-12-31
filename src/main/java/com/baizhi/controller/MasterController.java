package com.baizhi.controller;

import com.baizhi.entity.Master;
import com.baizhi.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * @ClassName MasterController
 * @Discription
 * @Acthor
 * @Date 2019/12/22  17:52
 */
@Controller
@RequestMapping("master")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @RequestMapping("/getMaster")
    @ResponseBody
    public void getMaster(HttpServletResponse response) throws Exception {
        // 设置编码格式
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        List<Master> masters = masterService.showAllMaster();
        StringBuilder sbu = new StringBuilder("<select>");
        for (Master master : masters) {
            sbu.append("<option value=\"" + master.getM_dharma() + "\">" + master.getM_dharma() + "</option>");
        }
        sbu.append("</select>");
        out.print(sbu.toString());
    }
}
