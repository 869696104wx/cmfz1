package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Discription
 * @Acthor
 * @Date 2019/12/22  9:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cmfz_user")
public class User implements Serializable {
    @Id
    @ExcelIgnore
    private String u_id;
    @Excel(name = "用户名")
    private String u_username;
    @ExcelIgnore
    private String u_password;
    @ExcelIgnore
    private String u_salt;
    @Excel(name = "法号")
    private String u_dharma;
    @Excel(name = "身份")
    private String u_province;
    @Excel(name = "城市")
    private String u_city;
    @Excel(name = "签名")
    private String u_sign;
    @Excel(name = "性别")
    private String u_sex;
    @Excel(name = "头像", type = 2, width = 20, height = 20)
    private String u_photo;
    @Excel(name = "状态")
    private String u_status;
    @Excel(name = "电话")
    private String u_phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "注册时间", format = "yyyy-MM-dd")
    private Date u_create_date;
    @Excel(name = "师从")
    private String m_name;
}
