create table `seller_info`(
	`seller_id` varchar(32) not null,
	`username` varchar(32) not null,
	`password` varchar(32) not null,
	`openid` varchar(64) not null comment "WeChat openid",
	`create_time` timestamp not null default current_timestamp comment "time created",
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment "time updated",
	primary key (`seller_id`)
) comment "seller information table";