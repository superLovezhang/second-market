package com.superflower.market.entity.vo;

import com.superflower.market.entity.Comment;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class CommentVo implements Serializable {

    private String id;

    private String replyId;

    private String goodsId;

    private String content;

    private String replyUsername;

    private String username;

    private String avatar;

    private Date createTime;

    private List<Comment> children;

}
