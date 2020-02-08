
DROP TABLE IF EXISTS meeting;

CREATE TABLE meeting
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
    title VARCHAR(30) NULL DEFAULT NULL COMMENT '会议名称',
    description VARCHAR(30) NULL DEFAULT NULL COMMENT '会议描述',
	start TIMESTAMP NOT NULL COMMENT '开始时间',
    end TIMESTAMP NOT NULL COMMENT '开始时间',
    location VARCHAR(30) NOT NULL COMMENT '地点',
    status int(2) not null default 1 comment '是否有效 1：有效',
    contactuser BIGINT(20) NOT NULL COMMENT '预定联系人',
	PRIMARY KEY (id)
);