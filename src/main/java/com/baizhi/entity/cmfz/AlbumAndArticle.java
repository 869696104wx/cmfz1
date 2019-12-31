package com.baizhi.entity.cmfz;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName AlbumAndArticle
 * @Discription
 * @Acthor
 * @Date 2019/12/26  16:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumAndArticle implements Serializable {
    private String Thumbnail;   //图片
    private String Title;        //标题
    private String author;        //描述
    private Integer Type;        //类型（0：闻，1：思）
    private Integer set_count;    //集数（只有闻的数据才有）
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date create_date;    //创建时间
}
