package com.tgem.ynnk.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
@TableName("permission_point")
public class PermissionPoint implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("point_class")
    private String pointClass;

    @TableField("point_icon")
    private String pointIcon;

    @TableField("point_status")
    private String pointStatus;


}
