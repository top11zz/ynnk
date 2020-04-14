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
@TableName("permission_menu")
public class PermissionMenu implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 展示图标
     */
    @TableField("menu_icon")
    private String menuIcon;

    /**
     * 排序号
     */
    @TableField("menu_order")
    private String menuOrder;


}
