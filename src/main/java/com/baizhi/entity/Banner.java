package com.baizhi.entity;

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
 * @ClassName Banner
 * @Discription
 * @Acthor
 * @Date 2019/12/18  14:45
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "banner")
public class Banner implements Serializable {
    @Id
    private String b_id;
    private String b_name;
    private String b_cover;
    private String b_describe;
    private String b_status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date b_create_date;
}
