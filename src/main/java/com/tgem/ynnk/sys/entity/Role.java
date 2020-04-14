package com.tgem.ynnk.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("role")
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 公司id
     */
    @TableField("company_id")
    private String companyId;

    /**
     * 新增时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
      @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableField("logic")
    private Integer logic;

    @TableField(exist = false)
    private Set<Permission> permissions = new HashSet<Permission>(0);//角色与模块  多对多
}
