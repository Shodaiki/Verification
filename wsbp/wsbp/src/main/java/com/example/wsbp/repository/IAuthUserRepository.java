package com.example.wsbp.repository;

import com.example.wsbp.data.AuthUser;

import java.util.List;

public interface IAuthUserRepository {

    /**
     * ユーザー名とパスワードをAuthUserテーブルに記録する
     *
     * @param userName ユーザー名
     * @param userPass パスワード
     * @return データベースの更新行数
     */
    public int insert(String userName, String userPass);

    public int delete(String userName);

    public boolean exists(String userName, String userPass);

    public List<AuthUser> find();

}
