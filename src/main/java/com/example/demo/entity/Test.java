package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test")
public class Test {

    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略设置为自增
    @Column(name = "id",nullable = false,unique = true) //列名称指定和列的约束
    private Long id;

    /** 专业id */
    @Basic
    @Column(name = "major",nullable = false)
    private Integer major;

    /** 题目指引 */
    @Basic
    @Column(name = "guide",nullable = false)
    private String guide;

    /** 题目 */
    @Basic
    @Column(name = "subject",nullable = false)
    private String subject;

    @Basic
    @Column(name = "status",nullable = false,columnDefinition = "tinyint default 0")
    private Integer status;
}
