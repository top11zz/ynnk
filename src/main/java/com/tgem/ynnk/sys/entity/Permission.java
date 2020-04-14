package com.tgem.ynnk.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

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
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("permission")
public class Permission implements Tree,Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 类型1:菜单 2:按钮
     */
    @TableField("type")
    private Integer type;

    @TableField("code")
    private String code;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    @TableField("pid")
    private String pid;

    /**
     * 可见状态
     */
    @TableField("en_visible")
    private String enVisible;

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
    private List childList;

}
