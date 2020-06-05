package com.superflower.market.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superflower.market.entity.Goods;
import com.superflower.market.entity.Result;
import com.superflower.market.entity.User;
import com.superflower.market.entity.vo.GoodsVo;
import com.superflower.market.service.GoodsService;
import com.superflower.market.service.UserService;
import com.superflower.market.util.OssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zz
 * @since 2020-05-08
 */
@RestController
@RequestMapping("/goods")
@CrossOrigin
@Api(tags = "商品管理")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;

    @ApiOperation("获取所有的商品")
    @PostMapping("/allGoods/{page}/{size}")
    public Result allGoods(@RequestBody(required = false) Map<String, String> map, @PathVariable Integer page, @PathVariable Integer size) {
        Page<Goods> goodsPage = new Page<>(page, size);
        IPage<Goods> iPage = goodsService.findAll(goodsPage, map);
        HashMap<String, Object> maps = new HashMap<>();
        List<Goods> records = iPage.getRecords();
        long pages = iPage.getPages();
        long current = iPage.getCurrent();
        long sizes = iPage.getSize();
        long total = iPage.getTotal();
        maps.put("page", pages);
        maps.put("current", current);
        maps.put("size", sizes);
        maps.put("total", total);
        maps.put("rows", records);
        return Result.success("查询成功", maps);
    }

    @ApiOperation("搜索商品")
    @PostMapping("/search/{page}/{size}")
    public Result search(@RequestBody(required = false) Map<String, Object> map, @PathVariable Integer page, @PathVariable Integer size) {
        page = page != null ? page : 1;
        size = size != null ? size : 8;
        IPage<Goods> iPage = goodsService.searchByQuery(map, page, size);
        Map<String, Object> maps = new HashMap<>();

        List<Goods> records = iPage.getRecords();
        long pages = iPage.getPages();
        long current = iPage.getCurrent();
        long sizes = iPage.getSize();
        long total = iPage.getTotal();
        maps.put("page", pages);
        maps.put("current", current);
        maps.put("size", sizes);
        maps.put("total", total);
        maps.put("rows", records);

        return Result.success("查询成功", maps);
    }

    @ApiOperation("获取当前登录用户的商品信息")
    @GetMapping("/obtainGoodsInfo")
    public Result obtainGoodsInfo(HttpServletRequest request) {
        String id = (String) request.getAttribute("id");
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("请登录，在操作");
        }
        GoodsVo goodsList = goodsService.findGoodsInfo(id);
        return Result.success("查询成功", goodsList);
    }

    @ApiOperation("根据日期获取售出商品数")
    @PostMapping("/showTimeGoodsNum")
    public Result showTimeGoodsNum(@RequestBody Map map) throws Exception {
        String date = (String) map.get("date");
        if (StringUtils.isEmpty(date)) {
            throw new Exception("请输入正确的参数");
        }
        Integer num = goodsService.getCurrentTimeGoodsNum(date);
        return Result.success("查询成功", num);
    }

    @ApiOperation("根据id获取商品信息")
    @GetMapping("/currentGoods/{id}")
    public Result currentGoods(@PathVariable String id) {
        Goods goods = goodsService.getById(id);
        return Result.success("查询成功", goods);
    }

    @ApiOperation("上传图片")
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        String fileName = OssUtil.upload(file);
        return Result.success("上传成功", fileName);
    }

    @ApiOperation("添加商品")
    @PostMapping("/addGoods")
    public Result addGoods(@RequestBody Goods goods, HttpServletRequest request) {
        String id = (String) request.getAttribute("id");
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("请登录，在操作");
        }
        User user = userService.getById(id);
        user.setSaleGoods(user.getSoldGoods() + 1);
        user.setPublishGoods(user.getPublishGoods() + 1);
        userService.updateById(user);
        String school = user.getSchool();
        String college = user.getCollege();
        String avatar = user.getAvatar();
        String username = user.getUsername();
        goods.setSchool(school);
        goods.setCollege(college);
        goods.setUserId(id);
        goods.setUsername(username);
        goods.setAvatar(avatar);
        goods.setUpdateTime(new Date());
        boolean b = goodsService.save(goods);
        return b ? Result.successWithoutData("发布成功") : Result.fail(2001l, "发布失败");
    }

    @ApiOperation("增加当前商品浏览次数")
    @GetMapping("/addViewCount/{id}")
    public Result addViewCount(@PathVariable String id) {
        Goods goods = goodsService.getById(id);
        goods.setViewCount(goods.getViewCount() + 1);
        boolean b = goodsService.updateById(goods);
        return b ? Result.successWithoutData("增加成功") : Result.fail(2001l,"增加失败");
    }

    @ApiOperation("编辑商品")
    @PostMapping("/editGoods/{id}")
    public Result editGoods(@RequestBody Goods goods, @PathVariable String id, HttpServletRequest request) {
        String userId = (String) request.getAttribute("id");
        if (StringUtils.isEmpty(userId)) {
            throw new IllegalArgumentException("请登录，在操作");
        }
        goods.setId(id);
        boolean b = goodsService.updateById(goods);
        return b ? Result.successWithoutData("编辑成功") : Result.fail(2001l, "编辑失败");
    }

    @ApiOperation("擦亮商品")
    @GetMapping("/polish")
    public Result polish(String id, HttpServletRequest request) {
        String userId = (String) request.getAttribute("id");
        if (StringUtils.isEmpty(userId)) {
            throw new IllegalArgumentException("请登录，在操作");
        }
        Goods goods = goodsService.getById(id);
        goods.setUpdateTime(new Date());
        goodsService.updateById(goods);
        return Result.successWithoutData("擦亮成功");
    }

    @ApiOperation("下架商品")
    @GetMapping("/offGoods")
    public Result offGoods(String id, HttpServletRequest request) {
        String userId = (String) request.getAttribute("id");
        if (StringUtils.isEmpty(userId)) {
            throw new IllegalArgumentException("请登录，在操作");
        }
        Goods goods = goodsService.getById(id);
        String userId1 = goods.getUserId();
        User user = userService.getById(userId1);
        user.setSaleGoods(user.getSoldGoods() - 1);
        user.setOffGoods(user.getOffGoods() + 1);
        userService.updateById(user);
        goods.setStatus(1);
        goodsService.updateById(goods);
        return Result.successWithoutData("下架成功");
    }

    @ApiOperation("上架商品")
    @GetMapping("/upGoods")
    public Result upGoods(String id, HttpServletRequest request) {
        String userId = (String) request.getAttribute("id");
        if (StringUtils.isEmpty(userId)) {
            throw new IllegalArgumentException("请登录，在操作");
        }
        Goods goods = goodsService.getById(id);
        String userId1 = goods.getUserId();
        User user = userService.getById(userId1);
        user.setSaleGoods(user.getSoldGoods() + 1);
        user.setOffGoods(user.getOffGoods() - 1);
        userService.updateById(user);

        goods.setStatus(0);
        goodsService.updateById(goods);
        return Result.successWithoutData("上架成功");
    }

    @ApiOperation("售出商品")
    @GetMapping("/sold")
    public Result sold(String id, HttpServletRequest request) {
        String userId = (String) request.getAttribute("id");
        if (StringUtils.isEmpty(userId)) {
            throw new IllegalArgumentException("请登录，在操作");
        }
        Goods goods = goodsService.getById(id);
        String userId1 = goods.getUserId();
        User user = userService.getById(userId1);
        user.setSaleGoods(user.getSaleGoods() - 1);
        user.setSoldGoods(user.getSoldGoods() + 1);
        userService.updateById(user);

        goods.setSoldStatus(1);
        goods.setUpdateTime(new Date());
        goodsService.updateById(goods);
        return Result.successWithoutData("售出成功");
    }

    @ApiOperation("删除商品")
    @GetMapping("/removeGoods")
    public Result remove(String id, HttpServletRequest request) {
        String userId = (String) request.getAttribute("id");
        if (StringUtils.isEmpty(userId)) {
            throw new IllegalArgumentException("请登录，在操作");
        }
        String userId1 = goodsService.getById(id).getUserId();
        User user = userService.getById(userId1);
        user.setStatus(0);
        userService.updateById(user);
        boolean b = goodsService.removeById(id);
        return b ? Result.successWithoutData("删除成功") : Result.fail(2001l, "删除失败");
    }

    @ApiOperation("推荐商品")
    @GetMapping("/recommendGoods")
    public Result recommendGoods(String id, HttpServletRequest request) {
        String userId = (String) request.getAttribute("id");
        if (StringUtils.isEmpty(userId)) {
            throw new IllegalArgumentException("请登录，在操作");
        }
        Goods goods = goodsService.getById(id);
        goods.setRecommend(1);
        boolean b = goodsService.updateById(goods);
        return b ? Result.successWithoutData("推荐成功") : Result.fail(2001l, "推荐失败");
    }

    @ApiOperation("取消推荐商品")
    @GetMapping("/cancelRecommendGoods")
    public Result cancelRecommendGoods(String id, HttpServletRequest request) {
        String userId = (String) request.getAttribute("id");
        if (StringUtils.isEmpty(userId)) {
            throw new IllegalArgumentException("请登录，在操作");
        }
        Goods goods = goodsService.getById(id);
        goods.setRecommend(0);
        boolean b = goodsService.updateById(goods);
        return b ? Result.successWithoutData("取消推荐成功") : Result.fail(2001l, "取消推荐失败");
    }

}

