package com.superflower.market.controller;


import com.superflower.market.entity.Result;
import com.superflower.market.entity.User;
import com.superflower.market.service.UserService;
import com.superflower.market.util.OssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/user")
@CrossOrigin
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody User user) throws Exception {
        userService.saveUser(user);
        return Result.successWithoutData("注册成功");
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody User user) throws Exception {
        Map map = userService.checkAndLogin(user);
        return Result.success("登录成功", map);
    }

    @ApiOperation("用户登出")
    @GetMapping("/logout")
    public Result logout() {
        return Result.successWithoutData("退出成功");
    }

    @ApiOperation("上传头像")
    @PostMapping("/uploadAvatar")
    public Result uploadAvatar(MultipartFile file, HttpServletRequest request) throws Exception {
        String id = (String) request.getAttribute("id");
        if (org.apache.commons.lang3.StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("请登录，在操作");
        }
        String fileName = OssUtil.upload(file);
        User user = new User();
        user.setId(id);
        user.setAvatar(fileName);
        boolean b = userService.updateById(user);
        return b ? Result.success("上传成功", fileName) : Result.fail(2001l, "上传失败");
    }

    @ApiOperation("获取当前用户登录信息")
    @GetMapping("/currentUserInfo")
    public Result currentUserInfo(HttpServletRequest request) {
        String id = (String) request.getAttribute("id");
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("请登录");
        }
        User user = userService.getById(id);
        user.setPassword(null);
        return Result.success("查询成功", user);
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/updateUserInfo")
    public Result updateUserInfo(@RequestBody User user, HttpServletRequest request) {
        String id = (String) request.getAttribute("id");
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("请登录");
        }
        user.setId(id);
        boolean b = userService.updateById(user);
        return b ? Result.successWithoutData("修改成功") : Result.fail(2001l, "修改失败");
    }

    @ApiOperation("修改用户密码")
    @PostMapping("/updateUserPassword")
    public Result updateUserPassword(@RequestBody Map map, HttpServletRequest request) {
        String id = (String) request.getAttribute("id");
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("请登录");
        }
        User user = userService.getById(id);
        String password = user.getPassword();
        String rowPassword = (String) map.get("rowPassword");
        String newPassword = (String) map.get("password");

        boolean b = BCrypt.checkpw(rowPassword, password);
        if (!b) {
            return Result.fail(2001l, "密码错误");
        }
        user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));

        userService.removeById(id);
        boolean save = userService.save(user);
        return save ? Result.successWithoutData("密码修改成功") : Result.fail(2001l, "密码修改失败");
    }

    @ApiOperation("获取用户列表")
    @GetMapping("/showUserList")
    public Result showUserList() {
        List<User> list = userService.list(null);
        return Result.success("查询成功", list);
    }

    @ApiOperation("冻结用户")
    @GetMapping("/freeze/{id}")
    public Result freeze(@PathVariable String id) {
        User user = userService.getById(id);
        user.setStatus(1);
        userService.updateById(user);
        return Result.successWithoutData("冻结成功");
    }

    @ApiOperation("解冻用户")
    @GetMapping("/unfreeze/{id}")
    public Result unfreeze(@PathVariable String id) {
        User user = userService.getById(id);
        user.setStatus(0);
        userService.updateById(user);
        return Result.successWithoutData("解冻成功");
    }


}

