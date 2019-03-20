package cn.smbms.controlle;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.user.UserServiceImpl;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@Api("用户列表")
@Controller
@RequestMapping("user")
public class UserControlle {
    @Resource
    private UserServiceImpl userService;
    @Resource
    private RoleService roleService;
    //用户登陆
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ApiOperation(value = "用户登陆",notes = "描述",httpMethod = "POST")
    public String login(String userCode,String userPassword,Model model){
        try {
            User user = userService.getUserByCode(userCode);
            if (user==null){
                model.addAttribute("error","用户名不存在！");
                return "login";
            }
            if (!user.getUserPassword().equals(userPassword)){
                model.addAttribute("error","密码错误！");
                return "login";
            }
            model.addAttribute("user",user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "frame";
    }
//跳转添加用户页面
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String addUser(){
        return "useradd";
    }
    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    public String  addUser(HttpServletRequest request,@Valid User user,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "addUser";
        }
        userService.insert(user);
        return "redirect:/user/list";
    }
    

    @RequestMapping(value = "update",method = RequestMethod.GET)
    public String updateUser(HttpServletRequest request,Integer userid,String username){
        User user=userService.getUser(userid,username);
        request.setAttribute("user",user);
        return "usermodify";
    }

    //更改用户信息 返回查询页面
    @RequestMapping(value = "updateUser",method = RequestMethod.POST)
    public String updateUser(HttpServletResponse response,@Valid User user,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "usermodify";
        }
        userService.update(user);
        return "redirect:/user/list";
    }
    @RequestMapping("look")
    public String lookUser(Model model,Integer userid,String username){
        User user=userService.getUser(userid,username);
        model.addAttribute("user",user);
        return "userview";
    }

    //用户的显示方法
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(@RequestParam(required = false)String userName,
                       @RequestParam(defaultValue = "0")Integer userRole,Model model){
        try {
            Integer roleid=null;
            if(userRole>0){
                roleid=userRole;
            }
            List<User> list=userService.getUserList(userName,roleid,0,100);
            List<Role> roleList=roleService.findRole(null,null,null);
            model.addAttribute("roleList",roleList);
            model.addAttribute("userList",list);
            model.addAttribute("userName",userName);
            model.addAttribute("userRole",userRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "userlist";
    }
//    删除用户信息
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public void delete(Integer uid,HttpServletResponse response) throws IOException {
        HashMap<String,String> hashMap=new HashMap<String,String>();
        if ( userService.delete(uid)){
            hashMap.put("delResult","true");
        }else{
            hashMap.put("delResult","false");
        }
        response.setContentType("application/json");
       PrintWriter outPrintWriter=response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(hashMap));
        outPrintWriter.flush();
        outPrintWriter.close();

    }
    //加载添加用户中的角色信息
    @RequestMapping(value = "/getRoleList",method = RequestMethod.GET)
    public void getRoleList(HttpServletResponse response)throws IOException{
        List<Role> roleList=roleService.findRole(null,null,null);
        response.setContentType("application/json");
        PrintWriter outPrintWriter=response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(roleList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }
    //添加用户进行用户判断是否存在
    @RequestMapping(value = "/getExistName",method = RequestMethod.GET)
    @ResponseBody
    public String isExist(String userCode,HttpServletResponse response)throws IOException{
        HashMap<String,Object> map=new HashMap<String,Object>();
        if (userService.getUserByName(userCode)==1){
           map.put("userCode","exist");
        }else{
            map.put("userCode","ok");
        }
      return JSONArray.toJSONString(map);
//        response.setContentType("application/json");
//        PrintWriter outPrintWriter=response.getWriter();
//        outPrintWriter.write(JSONArray.toJSONString(map));
//        outPrintWriter.flush();
//        outPrintWriter.close();
    }
    @RequestMapping("exit")
    public String exit(){
        return "login";
    }

    @RequestMapping("updatePwd")
    public String pwd(){
        return "pwdmodify";
    }
    @RequestMapping("pwd")
    public String pwd(Model model,User user){
        userService.update(user);
        return "redirect:/user/login";
    }

}
