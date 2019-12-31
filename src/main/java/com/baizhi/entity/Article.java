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
 * @ClassName article
 * @Discription
 * @Acthor
 * @Date 2019/12/22  15:58
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "article")
public class Article implements Serializable {
    @Id
    private String a_id;
    private String a_title;
    private String a_content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date a_create_date;
    private String a_author;
}
