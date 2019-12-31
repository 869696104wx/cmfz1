package com.baizhi.entity.cmfz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName Friend
 * @Discription
 * @Acthor
 * @Date 2019/12/26  20:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "friend")
public class Friend implements Serializable {
    @Id
    private String f_id;
    private String u_id1;
    private String u_id2;
}
