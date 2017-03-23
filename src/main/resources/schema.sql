CREATE TABLE IF NOT EXISTS jmx_nodes (
 node_id VARCHAR(30) NOT NULL AUTO_INCREMENT,
 node_name VARCHAR(30),
 hostname VARCHAR(100) NOT NULL,
 port INT(6) NOT NULL,
 auth_required BOOL NOT NULL,
 ssl_required BOOL NOT NULL,

 PRIMARY KEY (node_id)
);
