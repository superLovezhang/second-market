package com.superflower.market.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superflower.market.entity.Result;
import com.superflower.market.entity.User;
import com.superflower.market.entity.Want;
import com.superflower.market.mapper.UserMapper;
import com.superflower.market.service.UserService;
import com.superflower.market.service.WantService;
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
 * @since 2020-05-08
 */
@RestController
@RequestMapping("/want")
@CrossOrigin
@Api(tags = "求购商品管理")
public class WantController {

    @Autowired
    private WantService wantService;

    @Autowired
    private UserService userService;

    @ApiOperation("获取所有求购商品列表")
    @GetMapping("/showWantGoodsList")
    public Result showWantGoodsList() {
        QueryWrapper<Want> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete", 0);
        wrapper.orderByDesc("create_time");
        List<Want> list = wantService.list(wrapper);
        return Result.success("查询成功", list);
    }

    @ApiOperation("添加求购商品")
    @PostMapping("/addWantGoods")
    public Result addWantGoods(@RequestBody Want want, HttpServletRequest request) throws Exception {
        String id = (String) request.getAttribute("id");
        if (id == null) {
            throw new IllegalArgumentException("请登录");
        }
        if (
                StringUtils.isEmpty(want.getTitle()) ||
                StringUtils.isEmpty(want.getIntro()) ||
                want.getWantPrice() == null ||
                StringUtils.isEmpty(want.getWantAddress())
        ) {
            throw new Exception("请填写完整的参数");
        }
        User user = userService.getById(id);
        user.setWantGoods(user.getWantGoods() + 1);
        userService.updateById(user);
        wantService.saveWant(want, id);
        return Result.successWithoutData("发布成功");
    }

    @ApiOperation("删除自己发布的求购商品")
    @GetMapping("/removeMyGoods/{id}")
    public Result removeMyGoods(@PathVariable String id, HttpServletRequest request) {
        String userId = (String) request.getAttribute("id");
        if (userId == null) {
            throw new IllegalArgumentException("请登录");
        }
        wantService.deleteWantById(id, userId);
        return Result.successWithoutData("删除成功");
    }

}

