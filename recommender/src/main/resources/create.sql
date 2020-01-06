CREATE TABLE `recommender`.`user`(
                       `id` INT NOT NULL AUTO_INCREMENT,
                       `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       `updated_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       `phone` VARCHAR(40) NOT NULL DEFAULT '',
                       `password` VARCHAR(200) NOT NULL DEFAULT '',
                       `nick_name` VARCHAR(40) NOT NULL DEFAULT '',
                       `gender` INT NOT NULL DEFAULT 0,
                       PRIMARY KEY(`id`),
                       UNIQUE `phone_unique_index` USING BTREE(`phone`) COMMENT ''
) COMMENT = '';


CREATE TABLE `recommender`.`seller`  (
                                        `id` int(0) NOT NULL AUTO_INCREMENT,
                                        `name` varchar(80) NOT NULL DEFAULT '',
                                        `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        `updated_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        `rating` decimal(2, 1) NOT NULL DEFAULT 0 COMMENT '0~5',
                                        `disabled_flag` int(0) NOT NULL DEFAULT 0 COMMENT '0=enabled, 1=disabled',
                                        PRIMARY KEY (`id`)
);

CREATE TABLE `recommender`.`category`  (
                                          `id` int(0) NOT NULL AUTO_INCREMENT,
                                          `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                          `updated_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                          `name` varchar(20) NOT NULL DEFAULT '',
                                          `icon_url` varchar(200) NOT NULL DEFAULT '',
                                          `sort` int(0) NOT NULL DEFAULT 0,
                                          PRIMARY KEY (`id`),
                                          UNIQUE INDEX `name_unique_index`(`name`) USING BTREE
);

CREATE TABLE `recommender`.`store`  (
                                      `id` int(0) NOT NULL AUTO_INCREMENT,
                                      `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      `updated_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      `name` varchar(80) NOT NULL DEFAULT '',
                                      `rating` decimal(2, 1) NOT NULL DEFAULT 0 COMMENT '0~5',
                                      `price_per_consumer` int(0) NOT NULL DEFAULT 0,
                                      `latitude` decimal(10, 6) NOT NULL DEFAULT 0,
                                      `longitude` decimal(10, 6) NOT NULL DEFAULT 0,
                                      `category_id` int(0) NOT NULL DEFAULT 0,
                                      `tags` varchar(2000) NOT NULL DEFAULT '',
                                      `start_time` varchar(200) NOT NULL DEFAULT '',
                                      `end_time` varchar(200) NOT NULL DEFAULT '',
                                      `address` varchar(200) NOT NULL DEFAULT '',
                                      `seller_id` int(0) NOT NULL DEFAULT 0,
                                      `icon_url` varchar(100) NOT NULL DEFAULT '',
                                      PRIMARY KEY (`id`)
);