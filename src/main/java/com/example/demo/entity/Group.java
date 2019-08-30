package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "group")
public class Group {

    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略设置为自增
    @Column(name = "id",nullable = false,unique = true) //列名称指定和列的约束
    private Long id;

    /** 组名 */
    @Basic
    @Column(name = "name",nullable = false)
    private String name;

    /** 组描述 */
    @Basic
    @Column(name = "info",nullable = false)
    private String info;

}
