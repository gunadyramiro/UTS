-- phpMyAdmin SQL Dump
-- version 2.10.2
-- http://www.phpmyadmin.net
-- 
-- Host: localhost
-- Generation Time: March 11, 2021
-- Server version: 5.0.45
-- PHP Version: 5.2.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- Database: `cs`
-- 

-- --------------------------------------------------------

-- 
-- Table structure for table `data`
-- 

CREATE TABLE `data` (
  `no` varchar(10) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `jumlah` varchar(10) NOT NULL,
  PRIMARY KEY  (`no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `data`
-- 

INSERT INTO `data` VALUES ('B003', 'Sate Padang', '5');
INSERT INTO `data` VALUES ('B002', 'Es Kopi', '10');
INSERT INTO `data` VALUES ('B001', 'Mie Rebus', '25');
