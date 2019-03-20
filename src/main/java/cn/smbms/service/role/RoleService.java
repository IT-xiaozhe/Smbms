package cn.smbms.service.role;

import cn.smbms.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {
    int addRole(Role role);//添加角色
    int updateRole(Role role);//修改角色
    int deleteRole(@Param("id")Integer id);//删除角色
    //根据条件动态查询角色
    List<Role> findRole(@Param("roleCode")String roleCode, @Param("roleName")String roleName, @Param("createdBy")Integer createdBy);
}
