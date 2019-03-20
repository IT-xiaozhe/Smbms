package cn.smbms.service.role;

import cn.smbms.dao.role.RoleMapper;
import cn.smbms.pojo.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Override
    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public int deleteRole(Integer id) {
        return roleMapper.deleteRole(id);
    }

    @Override
    public List<Role> findRole(String roleCode, String roleName, Integer createdBy) {
        return roleMapper.findRole(roleCode, roleName, createdBy);
    }
}
