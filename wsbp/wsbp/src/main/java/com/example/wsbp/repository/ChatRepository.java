package com.example.wsbp.repository;

import com.example.wsbp.data.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class ChatRepository implements IChatRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public ChatRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int insert(String userName, String msgBody, Timestamp chatTime) {
        var sql = "insert into chat values (?, ?, ?)";
        var n = jdbc.update(sql, userName, msgBody, chatTime);
        return n;
    }

    @Override
    public int delete(String userName) {
        var sql = "delete from chat where user_name = ?";
        var n = jdbc.update(sql, userName);
        return n;
    }

    @Override
    public boolean exists(String userName, String msgBody) {
        // ユーザ名とパスワードが一致する情報が auth_user_fv テーブルにあれば、true を返す
        // テーブルになければ、何も返さない
        var sql = "select true from chat "
                + "where user_name = ? and msg_body = ?";

        // 検索用のSQLを実行する方法。検索結果をList（可変長配列）で返す。
        // データの追加時と若干異なるので注意。
        var booles = jdbc.query(sql,
                SingleColumnRowMapper.newInstance(Boolean.class),
                userName, msgBody);

        // Listにデータがある(＝trueの要素ものがある)：照合成功
        // Listにデータがない(要素が何もない)：照合失敗
        return !booles.isEmpty();
    }

    @Override
    public List<Chat> reply() {
        String sql = "select * from chat";

        // 検索用のSQLを実行する方法。
        // 取り出したいデータの型によって、第2引数の RowMapper を切り替える。
        // ? を使うSQLであれば、第3引数の Object型配列 の要素に順番に設定する。
        List<Chat> users = jdbc.query(sql,
                DataClassRowMapper.newInstance(Chat.class));

        // 取り出したデータ（Listの要素）をそのまま返値とする。
        return users;
    }

    @Override
    public List<Chat> selectreply(String userName) {
        String sql = "select * from chat where user_name = ?";

        // 検索用のSQLを実行する方法。
        // 取り出したいデータの型によって、第2引数の RowMapper を切り替える。
        // ? を使うSQLであれば、第3引数の Object型配列 の要素に順番に設定する。
        List<Chat> selectusers = jdbc.query(sql,
                DataClassRowMapper.newInstance(Chat.class), userName);

        // 取り出したデータ（Listの要素）をそのまま返値とする。
        return selectusers;
    }
}
