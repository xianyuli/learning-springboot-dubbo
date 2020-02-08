package com.xianyuli.dobbo.bootmeetingservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xianyuli.dobbo.bootmeetingservice.entity.Meeting;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xianyuli
 * @since 2019-12-07
 */
public interface IMeetingService extends IService<Meeting> {

    List<Meeting> list();
}
