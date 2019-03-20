package cn.smbms.controlle;

import cn.smbms.dao.bill.BillMapper;
import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.provider.ProviderService;
import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillControlle {
    @Resource
    private BillService billService;
    @Resource
    private ProviderService providerService;

    @RequestMapping("/lookJsp")
    public String look(Integer billid,String billcc,Model model){
        Bill bill= billService.getBill(billid,billcc);
        model.addAttribute("bill",bill);
        return "billview";
    }


    @RequestMapping("/delBill")
    public void del(Integer billid, HttpServletResponse response)throws IOException{
        HashMap<String,String> map=new HashMap<String,String>();
        if (billService.delete(billid)==1){
            map.put("delResult","true");
        }else{
            map.put("delResult","false");
        }
        response.setContentType("application/json");
        PrintWriter outPrintWriter=response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(map));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    @RequestMapping("/updateJsp")
    public String update(Integer billid,String billcc,Model model){
        Bill bill=billService.getBill(billid,billcc);
        model.addAttribute("bill",bill);
        return "billmodify";
    }
    @RequestMapping("/billList")
    public String count(Model model, String queryProductName, @RequestParam(defaultValue = "0") Integer queryProviderId,@RequestParam(defaultValue = "0",required = false) Integer queryIsPayment){
       Integer providerId=null;
       Integer isment=null;
       if (queryIsPayment>0){
           isment=queryIsPayment;
       }
        if (queryProviderId>0){
            providerId=queryProviderId;
        }
        List<Bill> bills=billService.getBillList(providerId,queryProductName,isment);
        List<Provider> providerList=providerService.findProvider(null,null,null,null);
        model.addAttribute("billList",bills);
        model.addAttribute("providerList",providerList);
        model.addAttribute("queryProviderId",queryProviderId);
        model.addAttribute("queryProductName",queryProductName);
        model.addAttribute("queryIsPayment",queryIsPayment);
        return "billlist";
           }

           @RequestMapping("/addJsp")
    public String addJsp(Model model){
        return "billadd";
           }

           @RequestMapping("/addPro")
    public void addBill(Model model,HttpServletResponse response)throws IOException{
               List<Provider> provider=providerService.findProvider(null,null,null,null);
               response.setContentType("application/json");
               PrintWriter outPrintWriter=response.getWriter();
               outPrintWriter.write(JSONArray.toJSONString(provider));
               outPrintWriter.flush();
               outPrintWriter.close();
           }
           @RequestMapping("addBill")
    public String addBill(Bill bill){
        billService.insert(bill);
        return  "redirect:/bill/billList";
           }
           @RequestMapping("updateBill")
    public String updateBill(Bill bill){
        billService.update(bill);
        return "redirect:/bill/billList";
           }
}
