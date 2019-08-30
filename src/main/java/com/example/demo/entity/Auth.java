package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auth")
public class Auth {

    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略设置为自增
    @Column(name = "id",nullable = false,unique = true) //列名称指定和列的约束
    private Long id;

    /** 权限字段 */
    @Basic
    @Column(name = "auth",nullable = false)
    private String auth;

    /** 权限模块 */
    @Basic
    @Column(name = "module",nullable = false)
    private String module;

    /** 组id */
    @Basic
    @Column(name = "group_id",nullable = false)
    private Integer groupId;
}
