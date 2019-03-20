package cn.smbms.controlle;

import cn.smbms.pojo.Provider;
import cn.smbms.service.provider.ProviderService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/provider")
public class ProviderControlle {
    @Resource
    private ProviderService providerService;
    //查询供应商列表
    @RequestMapping("/proList")
    public String getProList(String queryProCode,String queryProName,Model model){
        List<Provider> providerList=providerService.findProvider(queryProCode,queryProName,null,null);
        model.addAttribute("providerList",providerList);
        model.addAttribute("queryProCode",queryProCode);
        model.addAttribute("queryProName",queryProName);
        return "providerlist";
    }
    //跳转添加页面
    @RequestMapping("/addJsp")
    public String addJsp(){
        return "provideradd";
    }
    @RequestMapping("/updateJsp")
    public String updateJsp(Integer proid,String proname,Model model){
        Provider provider=providerService.getProvider(proid,proname);
        model.addAttribute("provider",provider);
        return "providermodify";
    }
    @RequestMapping("/lookJsp")
    public String lookJsp(Integer proid,String proname,Model model){
        Provider provider=providerService.getProvider(proid,proname);
        model.addAttribute("provider",provider);
        return "providerview";
    }
    //删除供应商信息
    @RequestMapping("delProvider")
    public void delProvider(Integer proid, HttpServletResponse response)throws IOException{
        HashMap<String,Object> map=new HashMap<String,Object>();
        System.out.println(proid);
        if (providerService.deleteProvider(proid)==1){
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
    //更新供应商信息
    @RequestMapping("/updatePro")
    public String updatePro(Provider provider){
        providerService.updateProvider(provider);
        return "redirect:/provider/proList";
    }
    //添加供应商信
    @RequestMapping("addPro")
    public String addPro(Provider provider){
        providerService.addProvider(provider);
        return "redirect:/provider/proList";
    }
}
