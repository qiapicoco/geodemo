-- 创建数据库geodemo，如果数据库已存在则不进行创建操作
CREATE DATABASE IF NOT EXISTS geodemo;
-- 使用geodemo数据库
USE geodemo;

-- 删除用户表，如果存在
DROP TABLE IF EXISTS UserTable;
-- 创建用户表
-- 该表用于存储系统中用户的基本信息
CREATE TABLE UserTable (
    user_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID，唯一标识每个用户，自增',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名，用于登录，非空且唯一',
    password VARCHAR(255) NOT NULL COMMENT '用户密码，加密存储，非空',
    email VARCHAR(100) UNIQUE COMMENT '用户邮箱，可用于找回密码等，唯一，可为空',
    phone_number VARCHAR(20) UNIQUE COMMENT '用户手机号码，可接收通知等，唯一，可为空',
    role VARCHAR(255) COMMENT '用户角色，如管理员、普通用户等，决定权限，可为空'
);

-- 删除权限表，如果存在
DROP TABLE IF EXISTS PermissionTable;
-- 创建权限表
-- 该表用于存储系统中各种权限的信息
CREATE TABLE PermissionTable (
    permission_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '权限ID，唯一标识每个权限，自增',
    permission_name VARCHAR(100) NOT NULL UNIQUE COMMENT '权限名称，标识权限，非空且唯一',
    permission_description VARCHAR(255) COMMENT '权限描述，解释权限具体作用，可为空'
);

-- 删除用户权限关联表，如果存在
DROP TABLE IF EXISTS UserPermissionAssociationTable;
-- 创建用户权限关联表
-- 该表用于建立用户和权限之间的多对多关系
CREATE TABLE UserPermissionAssociationTable (
    user_id INT COMMENT '用户ID，关联UserTable中的user_id',
    permission_id INT COMMENT '权限ID，关联PermissionTable中的permission_id',
    PRIMARY KEY (user_id, permission_id),
    FOREIGN KEY (user_id) REFERENCES UserTable(user_id),
    FOREIGN KEY (permission_id) REFERENCES PermissionTable(permission_id)
);

-- 删除地理信息数据表，如果存在
DROP TABLE IF EXISTS GeographicInformationDataTable;
-- 创建地理信息数据表
-- 该表用于存储地理信息数据的详细信息
CREATE TABLE GeographicInformationDataTable (
    data_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '数据ID，唯一标识每条地理信息数据记录，自增',
    data_name VARCHAR(255) COMMENT '数据名称，方便识别和管理，可为空',
    data_type VARCHAR(255) COMMENT '数据类型，如点数据、面数据等，可为空',
    data_description VARCHAR(255) COMMENT '数据描述，详细说明数据内容，可为空',
    data_file_path VARCHAR(255) COMMENT '数据文件路径，若数据以文件形式存储，记录存储路径，可为空',
    geographic_coordinate_info GEOMETRY COMMENT '地理坐标信息，存储地理信息的坐标，用于空间查询和分析'
);

-- 删除数据备份记录表，如果存在
DROP TABLE IF EXISTS DataBackupRecordTable;
-- 创建数据备份记录表
-- 该表用于记录地理信息数据的备份相关信息
CREATE TABLE DataBackupRecordTable (
    backup_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '备份ID，唯一标识每次数据备份记录，自增',
    data_id BIGINT COMMENT '数据ID，关联GeographicInformationDataTable中的data_id，表明备份对应的原始数据',
    backup_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '备份时间，记录备份操作的时间，默认当前时间',
    backup_path VARCHAR(255) COMMENT '备份路径，记录备份文件的存储路径，可为空',
    FOREIGN KEY (data_id) REFERENCES GeographicInformationDataTable(data_id)
);

-- 删除地图配置表，如果存在
DROP TABLE IF EXISTS MapConfigurationTable;
-- 创建地图配置表
-- 该表用于存储地图展示的配置信息
CREATE TABLE MapConfigurationTable (
    config_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '配置ID，唯一标识每个地图配置记录，自增',
    map_type VARCHAR(255) COMMENT '地图类型，如卫星地图、普通地图等，可为空',
    layer_info VARCHAR(255) COMMENT '图层信息，记录地图图层的相关内容，可为空'
);

-- 删除查询记录存储表，如果存在
DROP TABLE IF EXISTS QueryRecordStorageTable;
-- 创建查询记录存储表
-- 该表用于记录用户的查询操作记录
CREATE TABLE QueryRecordStorageTable (
    record_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '记录ID，唯一标识每次查询记录，自增',
    query_condition VARCHAR(255) COMMENT '查询条件，记录用户输入的查询条件，可为空',
    query_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '查询时间，记录查询操作执行的时间，默认当前时间',
    query_result_storage_path VARCHAR(255) COMMENT '查询结果存储路径，若查询结果以文件形式保存，记录存储路径，可为空'
);

-- 删除数据共享记录表，如果存在
DROP TABLE IF EXISTS DataSharingRecordTable;
-- 创建数据共享记录表
-- 该表用于记录数据共享的相关信息
CREATE TABLE DataSharingRecordTable (
    sharing_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '共享ID，唯一标识每次数据共享记录，自增',
    data_id BIGINT COMMENT '数据ID，关联GeographicInformationDataTable中的data_id，表明共享的数据',
    sharing_object VARCHAR(255) COMMENT '共享对象，可以是用户、部门或外部系统等，可为空',
    sharing_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '共享时间，记录数据共享的时间，默认当前时间',
    permission_level VARCHAR(255) COMMENT '权限级别，如公开、内部使用等，可为空',
    FOREIGN KEY (data_id) REFERENCES GeographicInformationDataTable(data_id)
);

-- 使用geodemo数据库
USE geodemo;

-- 插入用户管理模块 - 用户表数据
INSERT INTO UserTable (user_id, username, password, email, phone_number, role)
VALUES 
(1, 'admin', '$2a$10$wZ76p66cY2dW14DmXQVpIeQ7X6p7777q987654321', 'admin@example.com', '13800138000', 'administrator'),
(2, 'user1', '$2a$10$987654321abcdefghij', 'user1@example.com', '13900139000','regular_user');

-- 插入用户管理模块 - 权限表数据
INSERT INTO PermissionTable (permission_id, permission_name, permission_description)
VALUES 
(1, 'data_entry', '允许录入地理信息数据'),
(2, 'data_query', '允许查询地理信息数据'),
(3,'map_edit', '允许编辑地图配置');

-- 插入用户管理模块 - 用户权限关联表数据
INSERT INTO UserPermissionAssociationTable (user_id, permission_id)
VALUES 
(1, 1),
(1, 2),
(1, 3),
(2, 2);

-- 插入数据管理模块 - 地理信息数据表数据
-- 为使用空间数据类型，需确保数据库已开启相关支持（如MySQL的MyISAM或InnoDB存储引擎支持空间数据类型）
INSERT INTO GeographicInformationDataTable (data_id, data_name, data_type, data_description, data_file_path, geographic_coordinate_info)
VALUES 
(1, 'City Park', 'point', 'A beautiful park in the city', '/data/park_data.csv', ST_GeomFromText('POINT(116.4074 39.9042)')),
(2, 'River Boundary', 'linestring', 'The boundary of a river', '/data/river_data.shp', ST_GeomFromText('LINESTRING(116.41 39.91, 116.42 39.92, 116.43 39.93)'));

-- 插入数据管理模块 - 数据备份记录表数据
INSERT INTO DataBackupRecordTable (backup_id, data_id, backup_time, backup_path)
VALUES 
(1, 1, '2024-01-01 12:00:00', '/backup/park_data_20240101.bak'),
(2, 2, '2024-01-02 13:00:00', '/backup/river_data_20240102.bak');

-- 插入地图展示模块 - 地图配置表数据
INSERT INTO MapConfigurationTable (config_id, map_type, layer_info)
VALUES 
(1,'satellite', 'Satellite imagery layer'),
(2, 'normal', 'Normal map layer with road and building labels');

-- 插入查询分析模块 - 查询记录存储表数据
INSERT INTO QueryRecordStorageTable (record_id, query_condition, query_time, query_result_storage_path)
VALUES 
(1, 'data_type = ''point''', '2024-01-03 14:00:00', '/query_results/point_data_query_20240103.csv'),
(2, "data_name LIKE '%Park%'", '2024-01-04 15:00:00', '/query_results/park_data_query_20240104.csv');

-- 插入数据共享模块 - 数据共享记录表数据
INSERT INTO DataSharingRecordTable (sharing_id, data_id, sharing_object, sharing_time, permission_level)
VALUES 
(1, 1, 'external_company_A', '2024-01-05 16:00:00', 'public'),
(2, 2, 'internal_department_B', '2024-01-06 17:00:00', 'internal');