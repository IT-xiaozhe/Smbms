package cn.smbms.service.provider;

import cn.smbms.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderService {
    int addProvider(Provider provider);//添加供应商
    int updateProvider(Provider provider);//修改供应商
    int deleteProvider(@Param("id")Integer id);//根据ID修改供应商信息
    List<Provider> findProvider(@Param("code")String code,
                                @Param("name")String name,
                                @Param("contact")String contact,
                                @Param("proPhone")String proPhone);//动态查询
    //根据用户查询供应商对象
    Provider getProvider(Integer id,String name);
}
