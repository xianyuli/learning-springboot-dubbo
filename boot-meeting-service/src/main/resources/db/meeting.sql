DELETE FROM meeting;

INSERT INTO meeting ( id,title,description,start,end,location,contactUser) VALUES
(1, '日常会议1', '描述1',now(),now()+1,'101',1),
(2, '日常会议2', '描述', now(),now()+1,'201',2),
(3, '日常会议3', '描述', now(),now()+1,'301',3),
(4, '日常会议4', '描述', now(),now()+1,'401',4),
(5, '日常会议5', '描述', now(),now()+1,'1501',5);