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
 * @ClassName Chapter
 * @Discription
 * @Acthor
 * @Date 2019/12/19  15:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chapter")
public class Chapter implements Serializable {
    @Id
    private String chapter_id;
    private String chapter_title;
    private String chapter_size;
    private String chapter_duration;
    private String chapter_cover;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date chapter_create_date;
    private String album_id;
}