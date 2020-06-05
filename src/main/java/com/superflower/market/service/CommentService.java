package com.superflower.market.service;

import com.superflower.market.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.superflower.market.entity.vo.CommentVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zz
 * @since 2020-05-12
 */
public interface CommentService extends IService<Comment> {

    List<CommentVo> findCommentListById(String goodsId);

    void saveComment(Comment comment) throws Exception;

    void deleteCommentById(String id);
}
