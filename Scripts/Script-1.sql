create table shipping(
	order_no int auto_increment primary key,
	order_ID varchar(100),
	order_name varchar(100),
	order_place varchar(999),
	order_cate varchar(100),
	order_product varchar(300),
	order_cnt int,
	order_price int,
	order_date datetime,
	order_shipping varchar(100),
	order_state varchar(100),
	order_cancleDate datetime
);

insert into shipping (order_ID, order_name, order_place, order_cate, order_product, order_cnt, order_price, order_date, order_shipping, order_state, order_cancleDate)
values ("aaa","홍기훈","아케인리버 츄츄아일랜드","OUTWEAR","아케인견장",1,21000,sysdate(),"배송준비중","주문완료",null),
("bbb","홍니훈","아케인리버 츄츄아일랜드","OUTWEAR","아케인망토",2,22000,sysdate(),"배송준비중","주문취소",null),
("ccc","홍디훈","아케인리버 츄츄아일랜드","OUTWEAR","아케인전사장갑",3,23000,"2023-07-01","배송완료","환불",sysdate()),
("ddd","홍리훈","아케인리버 소멸의여로","TOP","아케인전사갑옷",4,24000,"2023-07-02","배송준비중","주문취소",null),
("eee","홍미훈","아케인리버 소멸의여로","TOP","아케인법사갑옷",5,25000,"2023-07-03","배송완료","환불",sysdate()),
("fff","홍비훈","아케인리버 소멸의여로","TOP","아케인해적갑옷",1,26000,"2023-07-04","배송완료","주문완료",null),
("ggg","홍시훈","아케인리버 리버스시티","BOTTOM","아케인전사하의",2,27000,"2023-07-05","배송완료","주문완료",null),
("hhh","홍이훈","아케인리버 리버스시티","BOTTOM","아케인법사하의",3,28000,"2023-07-01","배송준비중","주문취소",null),
("iii","홍지훈","아케인리버 리버스시티","BOTTOM","아케인해적하의",4,29000,"2023-07-02","배송완료","환불",sysdate()),
("jjj","홍치훈","아케인리버 아르카나","SHOES","아케인전사신발",5,30000,sysdate(),"배송준비중","주문완료",null),
("kkk","홍키훈","아케인리버 아르카나","SHOES","아케인법사신발",1,31000,"2023-07-03","배송준비중","주문취소",null),
("lll","홍티훈","아케인리버 아르카나","SHOES","아케인해적신발",2,32000,"2023-07-04","배송완료","환불",sysdate()),
("mmm","홍피훈","아케인리버 얌얌아일랜드","OUTWEAR","아케인전사망토",3,33000,"2023-07-05","배송중","주문완료",null),
("nnn","홍히훈","아케인리버 얌얌아일랜드","TOP","아케인도적상의",4,34000,sysdate(),"배송준비중","주문취소",null);

insert into shipping (order_ID, order_name, order_place, order_cate, order_product, order_cnt, order_price, order_date, order_shipping, order_state, order_cancleDate)
VALUES ("nnn","홍히훈","아케인리버 얌얌아일랜드","TOP","아케인도적상의",4,34000,sysdate(),"배송준비중","주문완료",null);

select * from shipping where order_shipping = '배송준비중'

update shipping set order_shipping = '배송완료' where order_ID = 'aaa'; 