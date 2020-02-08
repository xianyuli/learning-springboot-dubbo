package com.xianyuli.dubbo.bootdubboapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xianyuli.dubbo.bootdubboapi.entity.User;
import com.xianyuli.dubbo.bootdubboapi.mapper.UserMapper;
import com.xianyuli.dubbo.bootdubboapi.service.UserService;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xianyuli
 * @since 2019-12-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<User> queryUsers() {
        return baseMapper.queryUsers();
    }

    @Override
    public User getById(String userId) {
        return baseMapper.getById(userId);
    }
}
