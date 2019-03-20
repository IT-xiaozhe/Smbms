package cn.smbms.dao.address;

import cn.smbms.pojo.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    /**
     * 查询地址信息数量
     * @return
     */
    int count();

    /**
     * 通过id删除
     * @param id
     * @return
     */
    int deleteKey(@Param("id") Integer id);

    /**
     * 增加地址
     * @param address
     * @return
     */
    int insert(Address address);

    /**
     * 改
     * @param address
     * @return
     */
    int update(Address address);

    /**
     * 查询所有地址
     * @return
     */


    /**
     * 名字查地址
     * @param contact
     * @return
     */
    List<Address> getAddressList(@Param("contact") String contact);
}
