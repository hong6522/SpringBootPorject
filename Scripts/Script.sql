CREATE TABLE notice ( 
  no int(11) AUTO_INCREMENT, 
  title varchar(100) , -- 제목
  content varchar(9999), -- 내용
  photoFile varchar(100), -- 사진
  reg_date datetime, -- 등록일자
  id varchar(100) , -- 아이디
  notice_cnt int(11) , -- 게시물 조회수
  PRIMARY KEY (no)
) ;

CREATE TABLE qna (
    no int auto_increment primary key, -- 번호
    title varchar(100) , -- 제목
    type varchar(100), -- 환불/교환,주문/결제,회원정보,배송,상품확인 중 선택 
    content varchar(999) , -- 내용
    id varchar(100) , -- 질문 작성자 id
    qna_cnt int(11) , -- 조회수
    reg_date date, -- 질문 등록일
    getAnswer varchar(999), -- 관리자가 쓴 답변 
    answer_Date date, -- 답변 등록일
    admin_Id varchar(100) -- 답변 작성한 관리자id
);