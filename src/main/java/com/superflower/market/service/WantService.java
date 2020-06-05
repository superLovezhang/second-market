package com.superflower.market.service;

import com.superflower.market.entity.Want;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zz
 * @since 2020-05-08
 */
public interface WantService extends IService<Want> {

    void saveWant(Want want, String id) throws Exception;

    void deleteWantById(String id, String userId);
}
