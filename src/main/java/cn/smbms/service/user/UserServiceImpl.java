package cn.smbms.service.user;

import cn.smbms.dao.user.UserMapper;
import cn.smbms.pojo.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Service("userService")

public class UserServiceImpl implements IUserService{
    @Resource
    private UserMapper userMapper;


    @Override
    public int count() {
        return userMapper.count();
    }

    @Override
    public User getUserByCode(String userCode) {
        return userMapper.getUserByCode(userCode);
    }

    @Override
    public Integer getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }

    @Override
    public User getUser(Integer id, String userName) {
        return userMapper.getUser(id,userName);
    }

    @Override
    public List<User> getUserList(String userName, Integer userRole, Integer index, Integer size) {
        return userMapper.getUserList(userName, userRole, index, size);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public boolean delete(Integer id) {
        return userMapper.delete(id);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }
}
