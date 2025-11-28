-- Ch12. 스프링 앱에서의 데이터 소스 사용

-- DROP TABLE IF EXISTS purchase CASCADE;
--
-- CREATE TABLE IF NOT EXISTS purchase (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     product VARCHAR(50) NOT NULL,
--     price DOUBLE NOT NULL
-- );

-- Ch13. 스프링 앱에서 트랜잭션 사용
-- DROP TABLE IF EXISTS account CASCADE;
--
-- CREATE TABLE account (
--     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
--     name VARCHAR(50) NOT NULL,
--     amount DOUBLE NOT NULL
-- );

-- Ch14. 스프링 데이터로 데이터 영속성 구현
DROP TABLE IF EXISTS account CASCADE;

CREATE TABLE account (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    amount DOUBLE NOT NULL
);
