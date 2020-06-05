package com.superflower.market.controller;

import com.superflower.market.entity.Result;
import com.superflower.market.entity.User;
import com.superflower.market.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "后台用户管理")
@RestController
@CrossOrigin
@RequestMapping("/cms")
public class CmsController {

    @ApiOperation("后台登录")
    @PostMapping("/login")
    public Result login(@RequestBody Map map) {
        String username = (String) map.get("username");
        String password = (String) map.get("password");

        if (username.equals("admin") && password.equals("zz5201314")) {
            HashMap<String, String> hashMap = new HashMap<>();
            User user = new User();
            user.setUsername("张诏");
            user.setId("1");
            String token = JwtUtils.encode(user);
            hashMap.put("token", token);
           return Result.success("登陆成功", hashMap);
        }

        return Result.fail(2001,"密码或账号错误");
    }

    @ApiOperation("获取管理员权限")
    @GetMapping("/info")
    public Result getInfo() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "张诏");
        hashMap.put("roles", "管理员");
        return Result.success("获取成功", hashMap);
    }

}
