package cn.smbms.tools;

import java.util.Collection;

public class Teacher {
//    3、属性包括姓名，教工号，基本工资，岗位工资和绩效工资，方法包括信息输出，
//    设置姓名和教工号，设置三种工资金额，计算总工资(三种工资加起来)和税后工资(
//            按如下方式计算，3000以内不收税，3000-5000之间的部分扣10%，大于5000的部分扣15%)。在main方法中对方法进行测试(15分)
    String name;//姓名
    int num;//教工号
    int JbWages;//基本工资
    int GWWages;//岗位工资
    int JXWages;//绩效绩效

    @Override
    //信息输出
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", num=" + num +
                ", JbWages=" + JbWages +
                ", GWWages=" + GWWages +
                ", JXWages=" + JXWages +
                '}';
    }
    //设置姓名和工号
    public void setNameAndNum(String name,int num){
       this.name=name;
       this.num=num;
        System.out.println("设置成功：姓名："+name+",工号："+num);
    }
}
