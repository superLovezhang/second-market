package com.superflower.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.superflower.market.entity.User;
import com.superflower.market.entity.vo.UserVo;
import com.superflower.market.mapper.UserMapper;
import com.superflower.market.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superflower.market.util.JwtUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zz
 * @since 2020-05-08
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static String defaultAvatar = "https://iknow-pic.cdn.bcebos.com/b3b7d0a20cf431adeef1310e4d36acaf2edd98e1?x-bce-process=image/resize,m_lfit,w_600,h_800,limit_1";

    @Resource
    private UserMapper userMapper;

    @Override
    public void saveUser(User user) throws Exception {
        String password = user.getPassword();
        String schoolCode = user.getSchoolCode();
        String qq = user.getQq();
        // 对用户参数进行校验
        if (StringUtils.isEmpty(schoolCode)) {
            throw new Exception("必须填写学号");
        }
        if (StringUtils.isEmpty(password)) {
            throw new Exception("必须填写密码");
        }
        if (StringUtils.isEmpty(qq)) {
            throw new Exception("必须填写qq");
        }
        // 校验成功去数据库查看是否存在该用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("school_code", schoolCode);
        Integer integer = userMapper.selectCount(userQueryWrapper);
        if (integer != 0) {
            throw new Exception("该用户已存在");
        }
        String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(hashpw);
        user.setUsername("默认昵称" + UUID.randomUUID().toString().substring(0, 5));
        user.setAvatar(defaultAvatar);
        user.setCreateTime(new Date());
        this.save(user);
    }

    @Override
    public Map checkAndLogin(User user) throws Exception {
        String schoolCode = user.getSchoolCode();
        String password = user.getPassword();
        if (StringUtils.isEmpty(schoolCode)) {
            throw new Exception("必须填写学号");
        }
        if (StringUtils.isEmpty(password)) {
            throw new Exception("必须填写密码");
        }
        User userFromDb = getOne(new QueryWrapper<User>().eq("school_code", schoolCode));
        if (userFromDb == null) {
            throw new Exception("账号或者密码错误");
        }
        boolean b = BCrypt.checkpw(password, userFromDb.getPassword());
        if (!b) {
            throw new Exception("账号或者密码错误");
        }
        Integer status = userFromDb.getStatus();
        if (status == 1) {
            throw new Exception("账号冻结中");
        }
        String token = JwtUtils.encode(userFromDb);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userFromDb, userVo);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("userInfo", userVo);
        return map;
    }
}
