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
@Table(name = "task")
public class Task {
    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略设置为自增
    @Column(name = "id",nullable = false,unique = true) //列名称指定和列的约束
    private Long id;

    /** 任务名称 */
    @Basic //表示他是数据表中的一个列，可省
    @Column(name = "name",nullable = false)
    private String name;
    /** 任务类型 */
    @Basic
    @Column(name = "type",nullable = false)
    private Integer type;

    /**  作业程度 */
    @Basic
    @Column(name = "degree",nullable = false)
    private String degree;

    /** 科目 */
    @Basic
    @Column(name = "subject",nullable = false)
    private Integer subject;

    /** 作业字数 */
    @Basic
    @Column(name = "work_num",nullable = false)
    private Integer workNum;

    /** 计算字数 */
    @Basic
    @Column(name = "calculation_num",nullable = false)
    private Integer calculationNum;

    /** 到期时间 */
    @Basic
    @Column(name = "expire_time",nullable = false)
    private Date expireTime;

    /** 文献格式 */
    @Basic
    @Column(name = "document_format",nullable = false)
    private String documentFormat;

    /** 文献数量 */
    @Basic
    @Column(name = "document_num",nullable = false)
    private Integer documentNum;

    /** 客人微信 */
    @Basic
    @Column(name = "guest_wx",nullable = false)
    private String guestWx;

    /** 客人邮箱 */
    @Basic
    @Column(name = "guest_email",nullable = false)
    private String guestEmail;

    /** 售卖渠道 */
    @Basic
    @Column(name = "selling_channel",nullable = false)
    private String sellingChannel;

    /** 金额 */
    @Basic
    @Column(name = "sum_money",nullable = false)
    private Integer sumMoney;

    /** 欠款 */
    @Basic
    @Column(name = "arrears",nullable = false)
    private Integer arrears;

    /** 收款凭证 */
    @Basic
    @Column(name = "receipt_vouchers",nullable = false)
    private String receiptVouchers;

    /** 作业要求 */
    @Basic
    @Column(name = "operational_requirements",nullable = false)
    private String operationalRequirements;

    /** 评分准则 */
    @Basic
    @Column(name = "scoring_criteria",nullable = false)
    private String scoringCriteria;

    /** 附加资料 */
    @Basic
    @Column(name = "additional_information",nullable = false)
    private String additionalInformation;

    /** 备注 */
    @Basic
    @Column(name = "remarks",nullable = false)
    private String remarks;

    /** 额外服务 */
    @Basic
    @Column(name = "additional_services",nullable = false)
    private String additionalServices;

    @Basic
    @Column(name = "status",nullable = false,columnDefinition = "tinyint default 0")
    private Integer status;

    @Basic
    @Column(name = "task_status",nullable = false,columnDefinition = "tinyint default 0")
    private Integer taskStatus;

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
