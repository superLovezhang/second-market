package com.superflower.market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zz
 * @since 2020-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "学校名")
    private String school;

    @ApiModelProperty(value = "学号")
    private String schoolCode;

    @ApiModelProperty(value = "账户状态 0-正常 1-冻结")
    private Integer status;

    @ApiModelProperty(value = "qq号")
    private String qq;

    @ApiModelProperty(value = "学院")
    private String college;

    @ApiModelProperty(value = "年级")
    private String grade;

    @ApiModelProperty(value = "已发布商品数")
    private Integer publishGoods;

    @ApiModelProperty(value = "已售出商品数")
    private Integer soldGoods;

    @ApiModelProperty(value = "正在出售的商品数")
    private Integer saleGoods;

    @ApiModelProperty(value = "已下架商品数")
    private Integer offGoods;

    @ApiModelProperty(value = "求购商品数")
    private Integer wantGoods;

    @ApiModelProperty(value = "已举报商品数")
    private Integer reportGoods;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
