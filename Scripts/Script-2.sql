select * from shipping where order_date like concat('%','2023-07-09','%'); 

create table refund(
	orderNo int,	-- 주문번호
	re_orderID varchar(100), -- 사용자ID
	re_cate varchar(300), -- 상품카테고리
	re_name varchar(300), -- 상품명
	re_state varchar(300), -- 환불, 취소 종류
	re_reason varchar(999), -- 환불, 취소 사유
	re_answer varchar(100), -- 요청상태(답변 대기중, 취소·환불 완료, 취소·환불 불가능)
	re_impossible varchar(999) -- 환불, 취소 불가능 사유
);

insert into refund (orderNo, re_orderID, re_cate, re_name, re_state, re_reason, re_answer, re_impossible)
values (3, "ccc", "OUTWEAR", "아케인전사장갑", "환불", "상품에 결함이 있어 환불요청합니다.", "답변대기중", null),
(4, "ddd", "OUTWEAR", "아케인전사장갑", "환불", "상품에 결함이 있어 환불요청합니다.", "답변대기중", null);