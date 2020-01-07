package com.ukar.service;

import com.github.pagehelper.PageInfo;
import com.ukar.entity.User;

/**
 * Created by jyou on 2017/10/24.
 */
public interface UserService {
    User selectOne(long id);

    void update(User user);

    PageInfo<User> selectList();

    User insert(User user);

    void testTransactional();

    /**
     * load模式加载数据
     */
    void load(String command, String split);
}
