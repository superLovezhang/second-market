package com.superflower.market.controller;


import com.superflower.market.entity.Comment;
import com.superflower.market.entity.Result;
import com.superflower.market.entity.vo.CommentVo;
import com.superflower.market.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zz
 * @since 2020-05-12
 */
@RestController
@RequestMapping("/comment")
@CrossOrigin
@Api(tags = "评论管理")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation("根据商品id获取评论列表")
    @GetMapping("/showCommentList/{goodsId}")
    public Result showCommentList(@PathVariable String goodsId) {
        List<CommentVo> list = commentService.findCommentListById(goodsId);
        return Result.success("查询成功", list);
    }

    @ApiOperation("添加评论")
    @PostMapping("/addComment")
    public Result addComment(@RequestBody Comment comment, HttpServletRequest request) throws Exception {
        String userId = (String) request.getAttribute("id");
        if (userId == null) {
            throw new IllegalArgumentException("请登录");
        }
        comment.setUserId(userId);
        commentService.saveComment(comment);
        return Result.successWithoutData("添加成功");
    }

    @ApiOperation("删除评论")
    @GetMapping("/removeComment/{id}")
    public Result removeComment(@PathVariable String id, HttpServletRequest request) {
        String userId = (String) request.getAttribute("id");
        if (userId == null) {
            throw new IllegalArgumentException("请登录");
        }

        commentService.removeById(id);
        return Result.successWithoutData("删除成功");
    }

    @ApiOperation("获取所有评论列表")
    @GetMapping("/showAllCommentList")
    public Result showAllCommentList() {
        List<Comment> list = commentService.list(null);
        return Result.success("查询成功", list);
    }

}

