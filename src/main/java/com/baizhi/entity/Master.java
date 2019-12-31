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
 * @ClassName Master
 * @Discription
 * @Acthor
 * @Date 2019/12/22  17:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "master")
public class Master implements Serializable {
    @Id
    private String m_id;
    private String m_username;
    private String m_password;
    private String m_salt;
    private String m_dharma;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date m_create_date;
}
