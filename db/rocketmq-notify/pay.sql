CREATE DATABASE db_distributed_transaction_rocketmq_notify_pay;

USE db_distributed_transaction_rocketmq_notify_pay;

CREATE TABLE `account_pay` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL,
  `account_no` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '账号',
  `pay_amount` double DEFAULT NULL COMMENT '充值余额',
  `result` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '充值结果:success，fail',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;
