package com.xianyuli.dobbo.bootmeetingservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xianyuli.dobbo.bootmeetingservice.entity.Meeting;
import com.xianyuli.dobbo.bootmeetingservice.mapper.MeetingMapper;
import com.xianyuli.dobbo.bootmeetingservice.service.IMeetingService;
import com.xianyuli.dubbo.bootdubboapi.entity.User;
import com.xianyuli.dubbo.bootdubboapi.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xianyuli
 * @since 2019-12-07
 */
@Service
public class MeetingServiceImpl extends ServiceImpl<MeetingMapper, Meeting> implements IMeetingService {

    @Reference
    UserService userService;

    public List<Meeting> list() {
        List<Meeting> list = new ArrayList<>();
        List<Meeting> meetings = baseMapper.selectList();
        for (Meeting meeting : meetings) {
            Long uid = meeting.getContactuser();
            User user = userService.getById(uid);
            meeting.setUser(user);
            list.add(meeting);
        }
        return list;
    }
}
