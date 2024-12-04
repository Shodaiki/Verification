DROP TABLE if EXISTS CHAT;

CREATE TABLE CHAT(
    --ユーザ名
                          user_name VARCHAR(32) PRIMARY KEY,
    --チャット内容
                          msg_body TEXT NOT NULL,
    --チャット投稿時間
                          chat_time timestamp NOT NULL
)