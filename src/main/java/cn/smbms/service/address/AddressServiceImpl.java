package cn.smbms.service.address;

import cn.smbms.dao.address.AddressMapper;
import cn.smbms.pojo.Address;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("addressService")
public class AddressServiceImpl implements AddressService {
    @Resource
   private AddressMapper addressMapper;
    @Override
    public int count() {
        return addressMapper.count();
    }
    @Override
    public int deleteKey(Integer id) {
        return addressMapper.deleteKey(id);
    }
    @Override
    public int insert(Address address) {
        return addressMapper.insert(address);
    }
    @Override
    public int update(Address address) {
        return addressMapper.update(address);
    }
    @Override
    public List<Address> getAddressList(String contact) {
        return addressMapper.getAddressList(contact);
    }
}
