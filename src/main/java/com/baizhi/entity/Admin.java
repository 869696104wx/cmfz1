package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName Admin
 * @Discription
 * @Acthor
 * @Date 2019/12/17  15:08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin")
public class Admin implements Serializable {
    @Id
    private String ad_id;
    private String username;
    private String password;
    private String nickname;
}
