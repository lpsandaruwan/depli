INSERT INTO USER(id,username,password,is_enabled,last_password_reset_date) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 1, PARSEDATETIME('01-01-2016', 'dd-MM-yyyy'));

INSERT INTO authority(id,name) VALUES (1, 'ROLE_USER');
INSERT INTO authority(id,name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_authority(user_id,authority_id) VALUES (1, 1);
INSERT INTO user_authority(user_id,authority_id) VALUES (1, 2);

