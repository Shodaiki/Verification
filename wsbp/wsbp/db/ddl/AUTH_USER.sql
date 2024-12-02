DROP TABLE if EXISTS AUTH_USER;

CREATE TABLE AUTH_USER(
    --ユーザ名
                        user_name VARCHAR(32) PRIMARY KEY,
    --パスワード
                        user_pass VARCHAR(32) NOT NULL
)