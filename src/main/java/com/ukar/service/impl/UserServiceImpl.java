package com.ukar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ukar.entity.User;
import com.ukar.mapper.UserMapper;
import com.ukar.service.IoexService;
import com.ukar.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jyou on 2017/10/24.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private IoexService ioexService;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Override
    @Transactional
    public User selectOne(long id) {
        User user = new User();
        user.setId(id);
        User user1 = userMapper.selectOne(user);
        return user1;
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public PageInfo<User> selectList() {
        PageHelper.startPage(1, 2);
        List<User> list = userMapper.select(null);
        PageInfo<User> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public User insert(User user) {
        int i = userMapper.insertSelective(user);
        return null;
    }

    @Transactional
    public void testTransactional() {
        User user = new User();
        user.setName("test00000003");
        user.setPassword("456789");
        userMapper.insertSelective(user);
        try {
            ioexService.updateUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void load(String command, String split) {
        userMapper.load();

    }


}
