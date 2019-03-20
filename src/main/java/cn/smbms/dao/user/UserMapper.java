package cn.smbms.dao.user;

import cn.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userMapper")
public interface UserMapper {
    //获取用户数量
    int count();
    //根据用户名称和用户id查询用户信息
    User getUser(@Param("id")Integer id,@Param("userName")String userName);
    //通过账号查找信息
    User getUserByCode(@Param("userCode")String userCode);
    //通过编码查找用户信息
   Integer getUserByName(@Param("userCode")String userCode);
    //根据用户名称和角色ID查询用户列表
    List<User> getUserList(@Param("userName") String userName,
                           @Param("userRole") Integer userRole,
                           @Param("index") Integer index,
                           @Param("size") Integer size);
    User selectById(@Param("id") Integer id);
    boolean delete(@Param("id") Integer id);
    int update(User user);
    int insert(User user);
}
