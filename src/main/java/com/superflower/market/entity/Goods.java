package com.superflower.market.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
@ApiModel(value="Goods对象", description="")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品封面")
    private String cover;

    @ApiModelProperty(value = "商品介绍")
    private String intro;

    @ApiModelProperty(value = "购买价格")
    private BigDecimal buyPrice;

    @ApiModelProperty(value = "出售价格")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "二级分类")
    private String parentSubject;

    @ApiModelProperty(value = "二级分类")
    private String subject;

    @ApiModelProperty(value = "商品状态 0-商家 1-下架")
    private Integer status;

    @ApiModelProperty(value = "商品是否售出 0-未售出 1-已售出")
    private Integer soldStatus;

    @TableLogic
    @ApiModelProperty(value = "商品是否删除 0-正常 1-删除")
    private Integer isDelete;

    @ApiModelProperty(value = "是否推荐 0-未推荐 1-已推荐")
    private Integer recommend;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("浏览次数")
    private Integer viewCount;

    @ApiModelProperty(value = "更改时间")
    private Date updateTime;

    @ApiModelProperty("学校")
    private String school;

    @ApiModelProperty("学院")
    private String college;

}
