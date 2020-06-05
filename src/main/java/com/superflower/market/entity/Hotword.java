package com.superflower.market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
 * @since 2020-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Hotword对象", description="")
public class Hotword implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "热词主键id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "热词关键词")
    private String keyword;

    @ApiModelProperty(value = "热词搜索次数")
    private Integer count;

    @ApiModelProperty(value = "热词创建时间")
    private Date createTime;


}
