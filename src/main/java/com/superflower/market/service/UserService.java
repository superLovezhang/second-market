package com.superflower.market.service;

import com.superflower.market.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zz
 * @since 2020-05-08
 */
public interface UserService extends IService<User> {

    void saveUser(User user) throws Exception;

    Map checkAndLogin(User user) throws Exception;
}
