package com.xianyuli.dobbo.bootmeetingservice.controller;


import com.xianyuli.dobbo.bootmeetingservice.entity.Meeting;
import com.xianyuli.dobbo.bootmeetingservice.service.IMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xianyuli
 * @since 2019-12-07
 */
@RestController
@RequestMapping("/meeting/v1")
public class MeetingController {

    @Autowired
    IMeetingService meetingService;

    @GetMapping
    public List<Meeting> list() {
      return meetingService.list();
    }

}
