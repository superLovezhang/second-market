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
@ApiModel(value="Want对象", description="")
public class Want implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "求购商品id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "求购商品标题")
    private String title;

    @ApiModelProperty(value = "求购商品描述")
    private String intro;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户昵称")
    private String username;

    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "用户qq")
    private String qq;

    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @ApiModelProperty(value = "期望价格")
    private BigDecimal wantPrice;

    @TableLogic
    @ApiModelProperty(value = "商品是否删除 0-正常 1-删除")
    private Integer isDelete;

    @ApiModelProperty(value = "期望交易地址")
    private String wantAddress;


}
