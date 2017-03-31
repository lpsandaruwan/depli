CREATE TABLE IF NOT EXISTS auth_data(
 auth_id BIGINT AUTO_INCREMENT,
 username VARCHAR(50),
 password VARCHAR(50),

 PRIMARY KEY (auth_id),
);

CREATE TABLE IF NOT EXISTS jmx_nodes(
 node_id BIGINT AUTO_INCREMENT,
 auth_id BIGINT,
 node_name VARCHAR(30),
 hostname VARCHAR(100) NOT NULL,
 port INT(6) NOT NULL,
 auth_required BOOL NOT NULL,
 ssl_required BOOL NOT NULL,

 PRIMARY KEY (node_id),
 FOREIGN KEY (auth_id) REFERENCES auth_data(auth_id)
);

