package cn.smbms.service.provider;

import cn.smbms.dao.provider.ProviderMapper;
import cn.smbms.pojo.Provider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("providerService")
public class ProviderServiceImpl implements ProviderService {
    @Resource
    private ProviderMapper providerMapper;

    @Override
    public int addProvider(Provider provider) {
        return providerMapper.addProvider(provider);
    }

    @Override
    public int updateProvider(Provider provider) {
        return providerMapper.updateProvider(provider);
    }

    @Override
    public int deleteProvider(Integer id) {
        return providerMapper.deleteProvider(id);
    }

    @Override
    public List<Provider> findProvider(String code, String name, String contact, String proPhone) {
        return providerMapper.findProvider(code, name, contact, proPhone);
    }

    @Override
    public Provider getProvider(Integer id, String name) {
        return providerMapper.getProvider(id,name);
    }

}
