DROP TABLE if EXISTS AUTH_USER_FV;

CREATE TABLE AUTH_USER_FV(
    --ユーザ名
                        user_name VARCHAR(32) PRIMARY KEY,
    --パスワード
                        user_pass VARCHAR(32) NOT NULL
)