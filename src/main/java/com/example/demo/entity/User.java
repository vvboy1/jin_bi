package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User {

    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略设置为自增
    @Column(name = "id",nullable = false,unique = true) //列名称指定和列的约束
    private Long id;

    /** 姓名 */
    @Basic
    @Column(name = "person_name",nullable = false)
    private String personName;

    /** 用户名 */
    @Basic //表示他是数据表中的一个列，可省
    @Column(name = "username",nullable = false)
    private String username;

    /** 密码 */
    @Basic
    @Column(name = "password",nullable = false)
    private String password;

    /** 角色组id */
    @Basic
    @Column(name = "group_id",nullable = false)
    private Integer groupId;

    /** 学历 */
    @Basic
    @Column(name = "education",nullable = false)
    private String education;

    /** 专业 */
    @Basic
    @Column(name = "major",nullable = false)
    private Integer major;

    /** 电子邮箱 */
    @Basic
    @Column(name = "email",nullable = false)
    private String email;

    /** 电话 */
    @Basic
    @Column(name = "telephone",nullable = false)
    private String telephone;

    /** 简历 */
    @Basic
    @Column(name = "resume",nullable = false)
    private String resume;

    /** 证书 */
    @Basic
    @Column(name = "certificate",nullable = false)
    private String certificate;

    @Basic
    @Column(name = "status",nullable = false,columnDefinition = "tinyint default 0")
    private Integer status;

    /** 创建时间 */
    @Basic
    @Column(name = "create_time",nullable = false)
    @CreatedDate  //标识为创建时间
    private Date createTime;

    /** 更新时间 */
    @Basic
    @Column(name = "update_time",nullable = false)
    @LastModifiedDate  //标识为更新时间
    private Date updateTime;

}
