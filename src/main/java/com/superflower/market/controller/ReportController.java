package com.superflower.market.controller;


import com.superflower.market.entity.Report;
import com.superflower.market.entity.Result;
import com.superflower.market.entity.User;
import com.superflower.market.service.ReportService;
import com.superflower.market.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
 * @since 2020-05-10
 */
@RestController
@RequestMapping("/report")
@CrossOrigin
@Api(tags = "举报商品管理")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService userService;

    @ApiOperation("举报商品")
    @GetMapping("/reportGoods/{id}")
    public Result reportGoods(@PathVariable String id, @RequestParam String content, HttpServletRequest request) {
        String userId = (String) request.getAttribute("id");
        if (StringUtils.isEmpty(userId)) {
            throw new IllegalArgumentException("请先登录在进行操作");
        }
        User user = userService.getById(userId);
        user.setReportGoods(user.getReportGoods() + 1);
        userService.updateById(user);
        reportService.saveReport(id, userId, content);
        return Result.successWithoutData("举报成功");
    }

    @ApiOperation("举报商品列表")
    @GetMapping("/reportList")
    public Result reportList() {
        List<Report> list = reportService.list(null);
        return Result.success("查询成功", list);
    }

    @ApiOperation("删除举报信息")
    @GetMapping("/removeReport/{id}")
    public Result removeReport(@PathVariable String id) {
        reportService.removeById(id);
        return Result.successWithoutData("删除成功");
    }

}

