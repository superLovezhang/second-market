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
 * @since 2020-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Comment对象", description="")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论主键")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "回复id")
    private String replyId;

    @ApiModelProperty(value = "商品id")
    private String goodsId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "发表内容")
    private String content;

    @ApiModelProperty(value = "回复的评论人昵称")
    private String replyUsername;

    @ApiModelProperty(value = "评论人昵称")
    private String username;

    @ApiModelProperty(value = "评论人头像")
    private String avatar;

    @ApiModelProperty(value = "发表时间")
    private Date createTime;

}
