MySQL 연결법: https://dev-coco.tistory.com/6
lib 이후로 안해도 괜찮다



CREATE TABLE board (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    professor VARCHAR(255),
    credit INT,
    time VARCHAR(255)
);


1. MySQL 8.0 Command Line Client 실행
2. 미리 저장해둔 테이블인 board를 use 할 것임
use board;
3. Swing 실행 후 "저장" 버튼을 누른 뒤 MySQL에
select * from board;

4. 삭제하고 싶다면
delete from board;
