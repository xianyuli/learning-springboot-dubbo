package com.xianyuli.dobbo.bootmeetingservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.xianyuli.dubbo.bootdubboapi.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xianyuli
 * @since 2019-12-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Meeting implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 会议名称
     */
    private String title;

    /**
     * 会议描述
     */
    private String description;

    /**
     * 开始时间
     */
    private LocalDateTime start;

    /**
     * 开始时间
     */
    private LocalDateTime end;

    /**
     * 地点
     */
    private String location;

    /**
     * 是否有效 1：有效
     */
    private int status;
    /**
     * 预定联系人
     */
    private Long contactuser;

    private User user;


}
