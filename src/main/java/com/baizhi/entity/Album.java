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
 * @ClassName Album
 * @Discription
 * @Acthor
 * @Date 2019/12/19  15:04
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "album")
public class Album implements Serializable {
    @Id
    private String album_id;
    private String album_title;
    private String album_cover;
    private String album_author;
    private String album_beam;
    private Double album_score;
    private Integer album_count;
    private String album_intro;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date album_create_date;
}
