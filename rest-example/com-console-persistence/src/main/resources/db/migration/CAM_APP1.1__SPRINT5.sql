USE `campaign_app` ;


CREATE TABLE  `campaign_app`.`target_audience` (
  `audience_id` int(11) NOT NULL auto_increment,
  `audience_name` varchar(200) character set utf8 NOT NULL,
  `adult_composition` decimal(5,2) default NULL,
  `mri_set` varchar(2000) character set utf8 default NULL,
  `updated_date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `updated_by` varchar(100) default NULL,
  `is_draft` tinyint(1) unsigned NOT NULL,
  `npm_mri_relation` varchar(5) NOT NULL default 'AND',
  PRIMARY KEY  (`audience_id`),
  UNIQUE KEY `Audience_Name_UNIQUE` (`audience_name`)
);


CREATE TABLE  `campaign` (
  `campaign_id` int(11) NOT NULL auto_increment,
  `campaign_name` varchar(200) NOT NULL,
  `advertiser_id` varchar(100) NOT NULL,
  `cpm_goal` decimal(15,2) default NULL,
  `budget` decimal(15,2) default NULL,
  `audience_id` int(11) default NULL,
  `campaign_status` varchar(45) default NULL,
  `ranker_va_status` varchar(45) default NULL,
  `baseplan_file_name` varchar(100) default NULL,
  `updated_date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `updated_by` varchar(100) default NULL,
  `created_date` timestamp NULL default NULL,
  `created_by` varchar(100) default NULL,
  `campaign_type` varchar(15) default NULL,
  `buy_demo` varchar(100) default NULL,
  `grand_total_net_q1` decimal(15,2) default NULL,
  `grand_total_net_q2` decimal(15,2) default NULL,
  `grand_total_net_q3` decimal(15,2) default NULL,
  `grand_total_net_q4` decimal(15,2) default NULL,
  `grand_total_net_total` decimal(15,2) default NULL,
  `grand_total_gross_q1` decimal(15,2) default NULL,
  `grand_total_gross_q2` decimal(15,2) default NULL,
  `grand_total_gross_q3` decimal(15,2) default NULL,
  `grand_total_gross_q4` decimal(15,2) default NULL,
  `grand_total_gross_total` decimal(15,2) default NULL,
  `percent_by_quarter_q1` decimal(15,2) default NULL,
  `percent_by_quarter_q2` decimal(15,2) default NULL,
  `percent_by_quarter_q3` decimal(15,2) default NULL,
  `percent_by_quarter_q4` decimal(15,2) default NULL,
  `percent_by_quarter_total` decimal(15,2) default NULL,
  `net_actual_budget_q1` decimal(15,2) default NULL,
  `net_actual_budget_q2` decimal(15,2) default NULL,
  `net_actual_budget_q3` decimal(15,2) default NULL,
  `net_actual_budget_q4` decimal(15,2) default NULL,
  `net_actual_budget_total` decimal(15,2) default NULL,
  `net_planned_budget_q1` decimal(15,2) default NULL,
  `net_planned_budget_q2` decimal(15,2) default NULL,
  `net_planned_budget_q3` decimal(15,2) default NULL,
  `net_planned_budget_q4` decimal(15,2) default NULL,
  `net_planned_budget_total` decimal(15,2) default NULL,
  `net_plan_cpm_equiv_q1` decimal(15,2) default NULL,
  `net_plan_cpm_equiv_q2` decimal(15,2) default NULL,
  `net_plan_cpm_equiv_q3` decimal(15,2) default NULL,
  `net_plan_cpm_equiv_q4` decimal(15,2) default NULL,
  `net_plan_cpm_equiv_total` decimal(15,2) default NULL,
  `net_actual_cpm_equiv_q1` decimal(15,2) default NULL,
  `net_actual_cpm_equiv_q2` decimal(15,2) default NULL,
  `net_actual_cpm_equiv_q3` decimal(15,2) default NULL,
  `net_actual_cpm_equiv_q4` decimal(15,2) default NULL,
  `net_actual_cpm_equiv_total` decimal(15,2) default NULL,
  `net_plan_grps_equiv_q1` decimal(15,2) default NULL,
  `net_plan_grps_equiv_q2` decimal(15,2) default NULL,
  `net_plan_grps_equiv_q3` decimal(15,2) default NULL,
  `net_plan_grps_equiv_q4` decimal(15,2) default NULL,
  `net_plan_grps_equiv_total` decimal(15,2) default NULL,
  `net_actual_grps_equiv_q1` decimal(15,2) default NULL,
  `net_actual_grps_equiv_q2` decimal(15,2) default NULL,
  `net_actual_grps_equiv_q3` decimal(15,2) default NULL,
  `net_actual_grps_equiv_q4` decimal(15,2) default NULL,
  `net_actual_grps_equiv_total` decimal(15,2) default NULL,
  `net_index_q1` decimal(15,2) default NULL,
  `net_index_q2` decimal(15,2) default NULL,
  `net_index_q3` decimal(15,2) default NULL,
  `net_index_q4` decimal(15,2) default NULL,
  `net_index_total` decimal(15,2) default NULL,
  `net_unit_count_q1` decimal(15,2) default NULL,
  `net_unit_count_q2` decimal(15,2) default NULL,
  `net_unit_count_q3` decimal(15,2) default NULL,
  `net_unit_count_q4` decimal(15,2) default NULL,
  `net_unit_count_total` decimal(15,2) default NULL,
  `net_plan_cpm_raw_q1` decimal(15,2) default NULL,
  `net_plan_cpm_raw_q2` decimal(15,2) default NULL,
  `net_plan_cpm_raw_q3` decimal(15,2) default NULL,
  `net_plan_cpm_raw_q4` decimal(15,2) default NULL,
  `net_plan_cpm_raw_total` decimal(15,2) default NULL,
  `net_actual_cpm_raw_q1` decimal(15,2) default NULL,
  `net_actual_cpm_raw_q2` decimal(15,2) default NULL,
  `net_actual_cpm_raw_q3` decimal(15,2) default NULL,
  `net_actual_cpm_raw_q4` decimal(15,2) default NULL,
  `net_actual_cpm_raw_total` decimal(15,2) default NULL,
  `net_plan_grps_raw_q1` decimal(15,2) default NULL,
  `net_plan_grps_raw_q2` decimal(15,2) default NULL,
  `net_plan_grps_raw_q3` decimal(15,2) default NULL,
  `net_plan_grps_raw_q4` decimal(15,2) default NULL,
  `net_plan_grps_raw_total` decimal(15,2) default NULL,
  `net_actual_grps_raw_q1` decimal(15,2) default NULL,
  `net_actual_grps_raw_q2` decimal(15,2) default NULL,
  `net_actual_grps_raw_q3` decimal(15,2) default NULL,
  `net_actual_grps_raw_q4` decimal(15,2) default NULL,
  `net_actual_grps_raw_total` decimal(15,2) default NULL,
  `net_index_raw_q1` decimal(15,2) default NULL,
  `net_index_raw_q2` decimal(15,2) default NULL,
  `net_index_raw_q3` decimal(15,2) default NULL,
  `net_index_raw_q4` decimal(15,2) default NULL,
  `net_index_raw_total` decimal(15,2) default NULL,
  `is_directbuy_active` tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (`campaign_id`),
  UNIQUE KEY `campaign_name_UNIQUE` (`campaign_name`),
  KEY `fk_SELECTED_PARAMETER_AUDIENCE_TARGET1` (`audience_id`),
  CONSTRAINT `fk_SELECTED_PARAMETER_AUDIENCE_TARGET1` FOREIGN KEY (`audience_id`) REFERENCES `target_audience` (`audience_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


CREATE TABLE  `audience_parameter_master` (
  `audience_paramete_name` varchar(100) NOT NULL,
  `master_parameter_name` varchar(100) default NULL,
  `updated_date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `updated_by` varchar(100) default NULL,
  `audience_param_id` int(10) unsigned NOT NULL auto_increment,
  `is_default` tinyint(1) unsigned NOT NULL default '0',
  PRIMARY KEY  (`audience_param_id`)
) ;


insert into audience_parameter_master (audience_paramete_name, master_parameter_name, updated_date, updated_by, is_default) values ('GENDER', null, now(), 'SYSTEM', false);
insert into audience_parameter_master (audience_paramete_name, master_parameter_name, updated_date, updated_by, is_default) values ('M', 'GENDER', now(), 'SYSTEM', false);
insert into audience_parameter_master (audience_paramete_name, master_parameter_name, updated_date, updated_by, is_default) values ('F', 'GENDER', now(), 'SYSTEM', false);
INSERT INTO  audience_parameter_master (audience_paramete_name,updated_date,updated_by) VALUES ('COUNTY_SIZE',now(),'SYSTEM');
INSERT INTO  audience_parameter_master (master_parameter_name,audience_paramete_name,updated_date,updated_by) VALUES ('COUNTY_SIZE','A',now(),'SYSTEM');
INSERT INTO  audience_parameter_master (master_parameter_name,audience_paramete_name,updated_date,updated_by) VALUES ('COUNTY_SIZE','B',now(),'SYSTEM');
INSERT INTO  audience_parameter_master (master_parameter_name,audience_paramete_name,updated_date,updated_by) VALUES ('COUNTY_SIZE','C',now(),'SYSTEM');
INSERT INTO  audience_parameter_master (master_parameter_name,audience_paramete_name,updated_date,updated_by) VALUES ('COUNTY_SIZE','D',now(),'SYSTEM');
INSERT INTO  audience_parameter_master (audience_paramete_name,updated_date,updated_by) VALUES ('COUNTY_SIZE',now(),'SYSTEM');
INSERT INTO  audience_parameter_master (master_parameter_name,audience_paramete_name,updated_date,updated_by) VALUES ('COUNTY_SIZE','A',now(),'SYSTEM');
INSERT INTO  audience_parameter_master (master_parameter_name,audience_paramete_name,updated_date,updated_by) VALUES ('COUNTY_SIZE','B',now(),'SYSTEM');
INSERT INTO  audience_parameter_master (master_parameter_name,audience_paramete_name,updated_date,updated_by) VALUES ('COUNTY_SIZE','C',now(),'SYSTEM');
INSERT INTO  audience_parameter_master (master_parameter_name,audience_paramete_name,updated_date,updated_by) VALUES ('COUNTY_SIZE','D',now(),'SYSTEM');


CREATE TABLE  `run_date` (
  `run_date_id` int(11) NOT NULL auto_increment,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `budget` decimal(15,2) default NULL,
  `campaign_id` int(11) NOT NULL,
  `updated_date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `updated_by` varchar(100) default NULL,
  `budget_percent` decimal(15,2) default NULL,
  PRIMARY KEY  (`run_date_id`),
  KEY `fk_idx` (`campaign_id`),
  CONSTRAINT `fk_RUN_DATE` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`campaign_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


CREATE TABLE  `mri_parameter_master` (
  `mri_dictionary_id` varchar(100) default NULL,
  `mri_class` varchar(100) default NULL,
  `mri_category` varchar(100) default NULL,
  `mri_variable` varchar(200) default NULL,
  `mri_label` varchar(500) default NULL,
  `updated_date` timestamp NULL default CURRENT_TIMESTAMP,
  `updated_by` varchar(100) default NULL,
  `dictionary_month_start` timestamp NOT NULL default '0000-00-00 00:00:00',
  `dictionary_month_end` timestamp NOT NULL default '0000-00-00 00:00:00',
  `mri_id` bigint(20) unsigned NOT NULL auto_increment,
  PRIMARY KEY  (`mri_id`)
) ;


INSERT INTO  mri_parameter_master (mri_label,mri_class,mri_category,dictionary_month_start,dictionary_month_end,mri_dictionary_id,updated_date,updated_by) VALUES ('Medicated Skin Ointments, Creams, Lotions & Sprays: Total Users: Used in last 6 months: Neo to Go! CATEGORY: PersCareA CLASS: Adults','Adults','PersCareA','2014-10-01','2015-03-01','-2146287011',now(),'SYSTEM');
INSERT INTO  mri_parameter_master (mri_label,mri_class,mri_category,dictionary_month_start,dictionary_month_end,mri_dictionary_id,updated_date,updated_by) VALUES ('Women\'s Clothing: Big Ticket Items - Bought: : Cloth coat: In last 12 months: 3+ CATEGORY: ClothC CLASS: Adults','Adults','ClothC','2014-10-01','2015-03-01','-2143535085',now(),'SYSTEM');
INSERT INTO  mri_parameter_master (mri_label,mri_class,mri_category,dictionary_month_start,dictionary_month_end,mri_dictionary_id,updated_date,updated_by) VALUES ('Internet And Catalog Shopping - Ordered By (Mail/Phone): : Nordstrom: In last 12 months CATEGORY: ShoppingA CLASS: Adults','Adults','ShoppingA','2014-10-01','2015-03-01','-2139143566',now(),'SYSTEM');



CREATE TABLE  `parent_dbuy_network_master` (
  `p_dbuy_network_id` int(10) unsigned NOT NULL auto_increment,
  `network` varchar(100) NOT NULL,
  `parent_network` varchar(100) default NULL,
  `updated_date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `updated_by` varchar(100) NOT NULL,
  PRIMARY KEY  (`p_dbuy_network_id`)
) ;


INSERT INTO  parent_dbuy_network_master (network,parent_network,updated_date,updated_by) VALUES ('Al Jazeera Media Network',null,now(),'SYSTEM');
INSERT INTO  parent_dbuy_network_master (network,parent_network,updated_date,updated_by) VALUES ('Al Jazeera','Al Jazeera Media Network',now(),'SYSTEM');
INSERT INTO  parent_dbuy_network_master (network,parent_network,updated_date,updated_by) VALUES ('Grupo Salinas',null,now(),'SYSTEM');
INSERT INTO  parent_dbuy_network_master (network,parent_network,updated_date,updated_by) VALUES ('Azteca','Grupo Salinas',now(),'SYSTEM');
INSERT INTO  parent_dbuy_network_master (network,parent_network,updated_date,updated_by) VALUES ('Bounce Media',null,now(),'SYSTEM');
INSERT INTO  parent_dbuy_network_master (network,parent_network,updated_date,updated_by) VALUES ('BounceTV','Bounce Media',now(),'SYSTEM');


CREATE TABLE  `demo_campaigns` (
  `id` int(11) NOT NULL auto_increment,
  `campaign_id` int(11) NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_date` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `Index_campaign_unique` (`campaign_id`),
  CONSTRAINT `FK_demo_campaigns_1` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`campaign_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;