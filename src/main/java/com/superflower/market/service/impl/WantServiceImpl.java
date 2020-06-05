package com.superflower.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superflower.market.entity.User;
import com.superflower.market.entity.Want;
import com.superflower.market.mapper.UserMapper;
import com.superflower.market.mapper.WantMapper;
import com.superflower.market.service.WantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

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
public class WantServiceImpl extends ServiceImpl<WantMapper, Want> implements WantService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private WantMapper wantMapper;

    @Override
    public void saveWant(Want want, String id) throws Exception {
        User user = userMapper.selectById(id);
        want.setUserId(user.getId());
        want.setAvatar(user.getAvatar());
        want.setUsername(user.getUsername());
        want.setPhone(user.getPhone());
        want.setQq(user.getQq());
        want.setCreateTime(new Date());
        boolean b = save(want);
        if (!b) {
            throw new Exception("发布失败");
        }
    }

    @Override
    public void deleteWantById(String id, String userId) {
        User user = userMapper.selectById(userId);
        user.setWantGoods(user.getWantGoods() - 1);
        userMapper.updateById(user);
        QueryWrapper<Want> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("id", id);
        wantMapper.delete(wrapper);
    }
}
