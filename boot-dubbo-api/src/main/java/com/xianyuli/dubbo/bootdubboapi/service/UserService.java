package com.xianyuli.dubbo.bootdubboapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xianyuli.dubbo.bootdubboapi.entity.User;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xianyuli
 * @since 2019-12-05
 */
public interface UserService extends IService<User> {

    List<User> queryUsers();

    User getById(String userId);
}
