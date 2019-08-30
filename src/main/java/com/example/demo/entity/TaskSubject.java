package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_subject")
public class TaskSubject {

    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略设置为自增
    @Column(name = "id",nullable = false,unique = true) //列名称指定和列的约束
    private Long id;

    /** 作业科目 */
    @Basic
    @Column(name = "subject_name",nullable = false)
    private String subjectName;

    @Basic
    @Column(name = "status",nullable = false,columnDefinition = "tinyint default 0")
    private Integer status;
}
