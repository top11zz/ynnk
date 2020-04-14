package com.tgem.ynnk.sys.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lmx
 * @since 2020-03-30
 */
@Data
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("mobile")
    private String mobile;

    @TableField("account")
    private String account;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("enable_state")
    private Integer enableState;

    @TableField("company_id")
    private String companyId;

    @TableField("company_name")
    private String companyName;

//    @TableField("department_id")
//    private String departmentId;
//
//    @TableField("department_name")
//    private String departmentName;

    @TableField("level")
    private String level;

    @TableField("staff_photo")
    private String staffPhoto;

      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

      @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date updateTime;

    @TableField("logic")
    @TableLogic
    private Integer logic;

    @TableField(exist = false)
    private Set<Role> roles = new HashSet<Role>();//用户与角色   多对多

}
