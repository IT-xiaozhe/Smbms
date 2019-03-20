package cn.smbms.service.bill;

import cn.smbms.dao.bill.BillMapper;
import cn.smbms.pojo.Bill;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Transactional
@Service("billService")
public class BillServiceImpl implements BillService{
    @Resource
    private BillMapper billMapper;

    @Override
    public int count() {
        return billMapper.count();
    }

    @Override
    public List<Bill> billList() {
        return billMapper.billList();
    }

    @Override
    public List<Bill> getBillList(Integer providerId, String productName, Integer isPayment) {
        return billMapper.getBillList(providerId, productName, isPayment);
    }

    @Override
    public int delete(Integer id) {
        return billMapper.delete(id);
    }

    @Override
    public int insert(Bill bill) {
        return billMapper.insert(bill);
    }

    @Override
    public int update(Bill bill) {
        return billMapper.update(bill);
    }

    @Override
    public Bill getBill(Integer id, String code) {
        return billMapper.getBill(id,code);
    }
}
