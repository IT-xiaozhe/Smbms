package cn.smbms.dao.provider;

import cn.smbms.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper {
    int addProvider(Provider provider);//添加供应商
    int updateProvider(Provider provider);//修改供应商
    int deleteProvider(@Param("id")Integer id);//根据ID修改供应商信息
    List<Provider> findProvider(@Param("code")String code,
                                @Param("name")String name,
                                @Param("contact")String contact,
                                @Param("proPhone")String proPhone);//动态查询
    //根据条件查询供应商对象
    Provider getProvider(@Param("id") Integer id,@Param("proName") String name);
    //查询供应商名称

}
