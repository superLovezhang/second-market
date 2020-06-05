package com.superflower.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superflower.market.entity.Hotword;
import com.superflower.market.mapper.HotwordMapper;
import com.superflower.market.service.HotwordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zz
 * @since 2020-05-11
 */
@Service
public class HotwordServiceImpl extends ServiceImpl<HotwordMapper, Hotword> implements HotwordService {

    @Override
    public void increaseCount(String keyword) {
        // 根据关键词自动判断 数据库是否存在
        // 存在就增加count + 1
        // 不存在就创建保存
        QueryWrapper<Hotword> wrapper = new QueryWrapper<>();
        wrapper.eq("keyword", keyword);
        Hotword one = getOne(wrapper);
        if (one == null) {
            Hotword hotword = new Hotword();
            hotword.setKeyword(keyword);
            hotword.setCreateTime(new Date());
            save(hotword);
            return;
        }
        one.setCount(one.getCount() + 1);
        updateById(one);
    }
}
