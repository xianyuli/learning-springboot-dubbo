package com.xianyuli.dubbo.bootdubboapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xianyuli.dubbo.bootdubboapi.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author xianyuli
 * @since 2019-12-05
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM USER")
    List<User> queryUsers();

    @Select("SELECT * FROM USER WHERE id = #{userId}")
    User getById(String userId);
}
