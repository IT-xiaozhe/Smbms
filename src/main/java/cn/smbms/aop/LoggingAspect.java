package cn.smbms.aop;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;



@Aspect
public class LoggingAspect {
    public  static  final Logger log=Logger.getLogger(String.valueOf(LoggingAspect.class));
    @Pointcut("execution(* cn.smbms.service..*(..))")
    public void pointcut(){
    }
    //前置增强
    @Before("pointcut()")
    public  void beforeMethod(JoinPoint jp){
        log.info("前置增强-调用："+jp.getTarget()+"的"+jp.getSignature().getName()+
                "方法.方法入参:"+ Arrays.deepToString(jp.getArgs()));
    }
    //后置增强
    @AfterReturning(value = "pointcut()",returning = "result")
    public  void afterReturning(JoinPoint jp,Object result){
        log.info("后置增强-调用："+jp.getTarget()+"的"+jp.getSignature().getName()+
                "方法.方法返回值:"+result);
    }
    //异常增强
    @AfterThrowing(value = "pointcut()",throwing = "re")
    public void afterThrowing(JoinPoint jp,RuntimeException re){
        log.info("异常增强-调用："+jp.getSignature().getName()+"方法发生异常"+re);
    }
    //最终增强
    @After("pointcut()")
    public  void after(JoinPoint jp){
        log.info("最终增强-调用："+jp.getSignature().getName()+"方法执行结束");
    }

    public  void aroundLogger(ProceedingJoinPoint jp)throws  Throwable{
        log.info("前置增强-调用："+jp.getTarget()+"的"+jp.getSignature().getName()+
                "方法.方法入参:"+ Arrays.deepToString(jp.getArgs()));
        try{
            Object result=jp.proceed();//执行目标方法并获得其返回值
            log.info("后置增强-调用："+jp.getTarget()+"的"+jp.getSignature().getName()+
                    "方法.方法返回值:"+result);
        }catch (Throwable e){
            log.info("异常增强-调用："+jp.getSignature().getName()+"方法发生异常"+e);
        }finally {
            log.info("最终增强-调用："+jp.getSignature().getName()+"方法执行结束");
        }
    }
}
