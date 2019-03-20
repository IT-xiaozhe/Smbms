package cn.smbms.service.bill;

import cn.smbms.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BillService {
    /**
     * 查询订单表数据条数
     * @return
     */
    int count();

    /**
     * 查询所有订单信息
     * @return
     */
    List<Bill> billList();

    List<Bill> getBillList(@Param("providerId")Integer providerId,
                           @Param("productName") String productName,@Param("isPayment")Integer isPayment );
    /**
     * 通过ID 删除订单
     * @param id
     * @return
     */
    int delete(@Param("id") Integer id);

    /**
     * 添加订单信息
     * @param bill
     * @return
     */
    int insert(Bill bill);

    int update(Bill bill);
    Bill getBill(@Param("id") Integer id,@Param("billCode") String code);

}
