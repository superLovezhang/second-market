package com.superflower.market.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superflower.market.entity.Hotword;
import com.superflower.market.entity.Result;
import com.superflower.market.service.HotwordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zz
 * @since 2020-05-11
 */
@RestController
@RequestMapping("/hotword")
@CrossOrigin
@Api(tags = "热词管理")
public class HotwordController {

    @Autowired
    private HotwordService hotwordService;

    @ApiOperation("热词添加次数")
    @GetMapping("/addHotCount")
    public Result addHotCount(String keyword) {
        hotwordService.increaseCount(keyword);
        return Result.successWithoutData("增加成功");
    }

    @ApiOperation("获取当前热词列表")
    @GetMapping("/showHotWordList")
    public Result showHotWordList() {
        QueryWrapper<Hotword> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("count");
        wrapper.last("limit 5");
        List<Hotword> list = hotwordService.list(wrapper);
        return Result.success("查询成功", list);
    }

}

