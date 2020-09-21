/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50623
Source Host           : localhost:3306
Source Database       : dauem

Target Server Type    : MYSQL
Target Server Version : 50623
File Encoding         : 65001

Date: 2015-08-20 13:26:13
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `permissions`
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `description` text,
  `permissionname` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kja1mxwxcw188e9pusu6vk750` (`permissionname`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES ('1', '2015-08-19 23:09:33', '2015-08-19 23:09:33', 'O utilizador poderá registar e actualizar provincias.', 'GESTÃO_DE_PROVINCIAS');
INSERT INTO `permissions` VALUES ('2', '2015-08-19 23:09:33', '2015-08-19 23:09:33', 'O utilizador poderá registar e actualizar distritos.', 'GESTÃO_DE_DISTRITOS');
INSERT INTO `permissions` VALUES ('3', '2015-08-19 23:09:33', '2015-08-19 23:09:33', 'O utilizador poderá registar e actualizar locais.', 'GESTÃO_DE_LOCAIS');
INSERT INTO `permissions` VALUES ('4', '2015-08-19 23:09:33', '2015-08-19 23:09:33', 'O utilizador poderá registar e actualizar universidades.', 'GESTÃO_DE_UNIVERSIDADES');
INSERT INTO `permissions` VALUES ('5', '2015-08-19 23:09:33', '2015-08-19 23:09:33', 'O utilizador poderá registar e actualizar escolas pre-universitarias.', 'GESTÃO_DE_ESCOLAS_PRE_UNIVERSITARIAS');
INSERT INTO `permissions` VALUES ('6', '2015-08-19 23:09:33', '2015-08-19 23:09:33', 'O utilizador poderá registar e actualizar unidades organicas.', 'GESTÃO_DE_UNIDADE_ORGANICA');
INSERT INTO `permissions` VALUES ('7', '2015-08-19 23:09:33', '2015-08-19 23:09:33', 'O utilizador poderá registar e actualizar sectores.', 'GESTÃO_DE_SECTOR');
INSERT INTO `permissions` VALUES ('8', '2015-08-19 23:09:33', '2015-08-19 23:09:33', 'O utilizador poderá registar e actualizar cursos.', 'GESTÃO_DE_CURSO');
INSERT INTO `permissions` VALUES ('9', '2015-08-19 23:09:34', '2015-08-19 23:09:34', 'O utilizador poderá registar e actualizar disciplinas', 'GESTÃO_DE_DISCIPLINA');
INSERT INTO `permissions` VALUES ('10', '2015-08-19 23:09:34', '2015-08-19 23:09:34', 'O utilizador poderá criar, configurar os ciclos, adicionar fases e respectivos períodos de execução.', 'GESTÃO_DE_AGENTE');
INSERT INTO `permissions` VALUES ('11', '2015-08-19 23:09:34', '2015-08-19 23:09:34', 'O utilizador poderá registar e actualizar papeis do agente.', 'GESTÃO_DE_PAPEL_AGENTE');
INSERT INTO `permissions` VALUES ('12', '2015-08-19 23:09:34', '2015-08-19 23:09:34', 'O utilizador poderá, registar e actualizar contas.', 'GESTÃO_DE_CONTAS');
INSERT INTO `permissions` VALUES ('13', '2015-08-19 23:09:34', '2015-08-19 23:09:34', 'O utilizador poderá criar, configurar os ciclos, adicionar universidades e as respectivas disciplinas do ciclo.', 'GESTÃO_DE_CICLO');
INSERT INTO `permissions` VALUES ('14', '2015-08-19 23:09:34', '2015-08-19 23:09:34', 'O utilizador poderá validar o pre-registo.', 'GESTÃO_DE_VALIDACÃO_DE_PRE_REGISTO');
INSERT INTO `permissions` VALUES ('15', '2015-08-19 23:09:34', '2015-08-19 23:09:34', 'O utilizador poderá fazer monitoria', 'GESTÃO_DE_MONITORIA');
INSERT INTO `permissions` VALUES ('16', '2015-08-19 23:09:34', '2015-08-19 23:09:34', 'O utilizador poderá gerir todo relacionado aos agentes.', 'GESTÃO_DE_AGENTE_ADMIN');
INSERT INTO `permissions` VALUES ('17', '2015-08-19 23:09:34', '2015-08-19 23:09:34', 'O utilizador poderá exportar dados.', 'GESTÃO_DE_EXPORTACÃO_DE_DADOS');
INSERT INTO `permissions` VALUES ('18', '2015-08-19 23:09:34', '2015-08-19 23:09:34', 'O utilizador e administrador do sistema.', 'E_SUPER_ADMINISTRADOR');
INSERT INTO `permissions` VALUES ('19', '2015-08-19 23:09:34', '2015-08-19 23:09:34', 'O utilizador e administrador do sistema.', 'GESTÃO_DE_PRE_REGISTO');



-- ----------------------------
-- Table structure for `roles`
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `rolename` varchar(50) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jdhyvh8di85ai37phukfemdnx` (`rolename`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', '2015-08-19 23:09:34', '2015-08-19 23:09:34', 'Candidato', 'Normal');
INSERT INTO `roles` VALUES ('2', '2015-08-19 23:09:34', '2015-08-19 23:09:34', 'Administrador', 'Normal');

-- ----------------------------
-- Table structure for `role_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `role_permissions`;
CREATE TABLE `role_permissions` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`permission_id`,`role_id`),
  KEY `FK_d4atqq8ege1sij0316vh2mxfu` (`role_id`),
  CONSTRAINT `FK_d4atqq8ege1sij0316vh2mxfu` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK_qfkbccnh2c5o4tc7akq5x11wv` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permissions
-- ----------------------------
INSERT INTO `role_permissions` VALUES ('1', '1');
INSERT INTO `role_permissions` VALUES ('1', '2');
INSERT INTO `role_permissions` VALUES ('1', '3');
INSERT INTO `role_permissions` VALUES ('1', '4');
INSERT INTO `role_permissions` VALUES ('1', '5');
INSERT INTO `role_permissions` VALUES ('1', '6');
INSERT INTO `role_permissions` VALUES ('1', '7');
INSERT INTO `role_permissions` VALUES ('1', '8');
INSERT INTO `role_permissions` VALUES ('1', '9');
INSERT INTO `role_permissions` VALUES ('1', '10');
INSERT INTO `role_permissions` VALUES ('1', '11');
INSERT INTO `role_permissions` VALUES ('1', '12');
INSERT INTO `role_permissions` VALUES ('1', '13');
INSERT INTO `role_permissions` VALUES ('1', '14');
INSERT INTO `role_permissions` VALUES ('1', '15');
INSERT INTO `role_permissions` VALUES ('1', '16');
INSERT INTO `role_permissions` VALUES ('1', '17');
INSERT INTO `role_permissions` VALUES ('1', '18');
INSERT INTO `role_permissions` VALUES ('1', '19');

INSERT INTO `role_permissions` VALUES ('2', '19');


DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` longtext NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '2015-08-19 23:09:35', '2015-08-19 23:09:35', '', '$2a$10$g2rVSUFq8w7xYdqNnFjz.e8PJ2JBVz/RpEf3EPDLOs.B1Rxtwl9f.', 'admin@admin.com');

-- ----------------------------
-- Table structure for `user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_5q4rc4fh1on6567qk69uesvyf` (`role_id`),
  CONSTRAINT `FK_5q4rc4fh1on6567qk69uesvyf` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK_g1uebn6mqk9qiaw45vnacmyo2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES ('1', '1');
