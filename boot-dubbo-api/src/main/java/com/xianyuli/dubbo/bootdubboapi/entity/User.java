package com.xianyuli.dubbo.bootdubboapi.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author xianyuli
 * @since 2019-12-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 用户名
     */
    private String name;

    /**
     * 年齡
     */
    private int age;

    /**
     * 邮箱
     */
    private String email;



}
