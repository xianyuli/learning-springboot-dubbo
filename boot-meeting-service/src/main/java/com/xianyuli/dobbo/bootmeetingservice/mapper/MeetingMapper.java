package com.xianyuli.dobbo.bootmeetingservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xianyuli.dobbo.bootmeetingservice.entity.Meeting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xianyuli
 * @since 2019-12-07
 */
@Mapper
public interface MeetingMapper extends BaseMapper<Meeting> {

    @Select("SELECT * FROM MEETING")
    List<Meeting> selectList();
}
