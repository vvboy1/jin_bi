package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_type")
public class TaskType {

    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略设置为自增
    @Column(name = "id",nullable = false,unique = true) //列名称指定和列的约束
    private Long id;

    /** 作业类型名字 */
    @Basic
    @Column(name = "task_type",nullable = false)
    private String taskType;

    @Basic
    @Column(name = "status",nullable = false,columnDefinition = "tinyint default 0")
    private String status;

}
