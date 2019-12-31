package com.baizhi.entity.cmfz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Cmfz_Chapter
 * @Discription
 * @Acthor
 * @Date 2019/12/26  18:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cmfz_Chapter implements Serializable {
    private String title;                //第几集
    private String download_url;         //下载地址
    private String size;                 //音频大小（字节数）
    private String duration;             //音频时长（毫秒数）
}
