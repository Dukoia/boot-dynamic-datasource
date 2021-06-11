DROP TABLE IF EXISTS `pico_promote_image`;
CREATE TABLE `pico_promote_image`
(
    `id`           BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `subject_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主题名称',
    `image_url`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片URL',
    `image_type`   tinyint(4) NULL DEFAULT NULL COMMENT '图片类型',
    `status`       tinyint(4) NULL DEFAULT 1 COMMENT '状态 0可用 1过期',
    `create_user`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `update_user`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
    `create_time`  datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`  datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;