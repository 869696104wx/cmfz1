package com.baizhi.entity.cmfz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Cmfz_Banner
 * @Discription
 * @Acthor
 * @Date 2019/12/26  17:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cmfz_Banner implements Serializable {
    private String thumbnail;  //头图描述
    private String desc;  //头图描述
    private String Id;    //头图id
}
