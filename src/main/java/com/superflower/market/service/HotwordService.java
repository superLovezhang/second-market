package com.superflower.market.service;

import com.superflower.market.entity.Hotword;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zz
 * @since 2020-05-11
 */
public interface HotwordService extends IService<Hotword> {

    void increaseCount(String keyword);
}
