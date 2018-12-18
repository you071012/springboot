package com.ukar.service;

import com.github.pagehelper.PageInfo;
import com.ukar.entity.User;

/**
 * Created by jyou on 2017/10/24.
 */
public interface UserService {
    User selectOne(long id);

    User update(User user);

    PageInfo<User> selectList();

    User insert(User user);

    void testTransactional();
}
