create table user_tab
(
	id bigint unsigned primary key,
	username varchar(50) not null comment '用户名'
	nickname varchar(50) not null comment '昵称'
	password varchar(128) not null comment '密码'
	salt varchar(128) not null comment '加密盐'
	phone varchar(20) not null comment '用户手机号'
  	ctime int unsigned not null comment '创建时间'
	mtime int unsigned not null comment '更新时间'
)
create index idx_username on user_tab;

create table user_address_tab
(
	id bigint unsigned primary key,
	user_id bigint unsigned not null comment '用户表id'
	address varchar(256) not null comment '收货地址'
	contact_name varchar(20) not null comment '联系人名称'
	contact_phone varchar(20) not null comment '联系人手机'
	province varchar(30) not null comment '省'
	city	varchar（128） not null comment '市'
	county varchar(128) not null comment '区/县' 
	post_code varchar(6) not null comment '邮政编码'
  	ctime int unsigned not null comment '创建时间'
	mtime int unsigned not null comment '更新时间'
)
create index idx_user_id on user_address_tab;

create table user_order_tab
(
	id bigint unsigned primary key,
	user_id bigint unsigned not null comment '用户表id'
	address_id bigint unsigned not null comment '地址id'
	order_no varchar(48) not null comment '订单号'
	goods_id bigint unsigned not null comment '商品id'
	order_status int unsigned not null comment '订单状态 ：1创建，2付款，3已发货，4确认收货，5已评价'
	count int not null comment '购买数量'
	discount_amount int  not null comment '优惠金额'
	price int not null comment '单价' 
  	ctime int unsigned not null comment '创建时间'
	mtime int unsigned not null comment '更新时间'
)
create index idx_order_no on user_order_tab;

create index logistics_info_tab(
	id bigint unsigned primary key,
	user_order_no_id bigint unsigned comment '用户订单id'
	logistics_order_no varchar(40) not null comment '物流单号'
	ctime int unsigned not null comment '创建时间'
	mtime int unsigned not null comment '更新时间'
)
create index idx_user_order_no_id  on logistics_info_tab;

create table shop_goods_tab
(
	id bigint unsigned primary key,
	category_id bigint unsigned not null comment '分类id'
	address varchar(256) not null comment '收货地址'
	goods_name varchar(40) not null comment '名称'
	price	int unsigned  not null comment '价格'
	stock_num int unsigned not null comment '库存' 
	goods_desc varchar(512) not null comment '描述'
  	ctime int unsigned not null comment '创建时间'
	mtime int unsigned not null comment '更新时间'
)