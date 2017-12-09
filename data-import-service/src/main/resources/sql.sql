DROP TABLE IF EXISTS `data_info`;
create table data_info (
  id INT(20) not NULL PRIMARY KEY auto_increment,
  title VARCHAR(255),
  data_type VARCHAR(20),
  data_from VARCHAR(30)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;