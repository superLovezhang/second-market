package com.superflower.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superflower.market.entity.Comment;
import com.superflower.market.entity.vo.CommentVo;
import com.superflower.market.mapper.CommentMapper;
import com.superflower.market.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zz
 * @since 2020-05-12
 */
@Service
@Transactional
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


    @Override
    public List<CommentVo> findCommentListById(String goodsId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id", goodsId);
        List<Comment> commentList = baseMapper.selectList(wrapper);

        ArrayList<CommentVo> commentVos = new ArrayList<>();
        // 找到一级评论
        for (Comment comment : commentList) {
            String replyId = comment.getReplyId();
            if ("0".equals(replyId)) {
                CommentVo commentVo = new CommentVo();
                BeanUtils.copyProperties(comment, commentVo);
                commentVo.setChildren(new ArrayList<Comment>());
                commentVos.add(commentVo);
            }
        }
        // 遍历一级评论 找到child元素
        for (CommentVo commentVo : commentVos) {
            for (Comment comment : commentList) {
                String id = commentVo.getId();
                String replyId = comment.getReplyId();
                if (id.equals(replyId)) {
                    List<Comment> children = commentVo.getChildren();
                    children.add(comment);
                }
            }
        }

        return commentVos;
    }

    @Override
    public void saveComment(Comment comment) throws Exception {
        String avatar = comment.getAvatar();
        String content = comment.getContent();
        String goodsId = comment.getGoodsId();
        String username = comment.getUsername();
        String replyUsername = comment.getReplyUsername();

        if (StringUtils.isEmpty(avatar) && StringUtils.isEmpty(content) && StringUtils.isEmpty(goodsId) && StringUtils.isEmpty(username) && StringUtils.isEmpty(replyUsername)) {
            throw new Exception("参数请填写完整");
        }

        comment.setCreateTime(new Date());
        save(comment);
    }

    @Override
    public void deleteCommentById(String id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        baseMapper.delete(wrapper);
    }
}
