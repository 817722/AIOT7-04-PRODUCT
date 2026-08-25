-- 계정 생성
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;
CREATE USER aloha IDENTIFIED BY 123456;
ALTER USER aloha DEFAULT TABLESPACE users;
ALTER USER aloha QUOTA UNLIMITED ON users;
GRANT DBA TO aloha;
-- 계정 삭제
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;
DROP USER aloha CASCADE;

-- 테이블 삭제
DROP TABLE product;

-- 테이블 생성
CREATE TAble product(
    no                  NUMBER          PRIMARY KEY,
    title               VARCHAR2(200)   NOT NULL,
    writer              VARCHAR2(200)   NOT NULL,
    content             CLOB,
    created_at          DATE            DEFAULT sysdate,
    updated_at          DATE            DEFAULT sysdate
    );

    -- 시퀀스
    CREATE SEQUENCE produt_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCYCLE 
    NOCACHE;
    

    