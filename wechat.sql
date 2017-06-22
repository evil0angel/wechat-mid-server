SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `wx_tokens`
-- ----------------------------
DROP TABLE IF EXISTS `wx_tokens`;
CREATE TABLE `wx_tokens` (
  `wx_id` varchar(64) NOT NULL,
  `appid` varchar(255) NOT NULL,
  `secret` varchar(255) NOT NULL,
  `access_token` varchar(255) NOT NULL,
  `expires_in` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `refresh_in` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `refresh_times` int(11) NOT NULL DEFAULT '0',
  `status` varchar(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`wx_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
