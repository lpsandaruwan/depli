CREATE TABLE IF NOT EXISTS jmx_nodes(
 node_id BIGINT AUTO_INCREMENT,
 node_name VARCHAR(30),
 hostname VARCHAR(100) NOT NULL,
 port INT(6) NOT NULL,
 username VARCHAR(50),
 password VARCHAR(50),
 auth_required BOOL NOT NULL,
 ssl_required BOOL NOT NULL,

 PRIMARY KEY (node_id)
);
