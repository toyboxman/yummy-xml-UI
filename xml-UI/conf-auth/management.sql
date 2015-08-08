CREATE   TABLE   terminalinfo  
(
terminalno      varchar(16) NOT NULL PRIMARY KEY,     --终端号
terminalip      varchar(16),      --ip
terminalstatus  integer,      --终端状态
terminalid      varchar(16),      --认证信息
terminaladdr    varchar(128),  --终端地址
terminalcall    varchar(16)      --问题联系人电话
);

CREATE INDEX IDX_HID_terminalinfo on terminalinfo (terminalno);

CREATE   TABLE   terminaldevice
(
terminalno      varchar(16) NOT NULL PRIMARY KEY,   --终端编号
printerstate    integer,    --打印机状态
inicstate       integer,     --ic刷卡器状态
keyboardstate   integer,  -- 小键盘状态
magstate        integer,     --词条刷卡器状态
CONSTRAINT fk_terminalinfo FOREIGN KEY (terminalno)
REFERENCES terminalinfo(terminalno)
);

CREATE INDEX IDX_HID_terminaldevice
on terminaldevice (terminalno);

----  设备状态 可以在注册时候带上来， 我给你更新进数据库。
----  显示有哪些终端
----  终端状态（是否login）
----- 能显示指定终端上外设状态。（以后发现外设不对会给管理员发短信提醒，发短信这个暂时不实现）
----- 最后能看每个终端的交易统计。（先考虑暂时不实现，以后方便再加）
----- 简单的权限，登录 admin 

CREATE   TABLE   terminalmgr  
(
name      varchar(16) NOT NULL PRIMARY KEY,     --管理用户名
credentials  varchar(16)      --密码
);