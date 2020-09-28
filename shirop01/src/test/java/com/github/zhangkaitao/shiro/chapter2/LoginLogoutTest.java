package com.github.zhangkaitao.shiro.chapter2;

import com.github.huyunhui.util.PrintStackTrace;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by dell on 2020/9/24.
 */
public class LoginLogoutTest {

    @Test
public void testHelloworld() {
    //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
    Factory<SecurityManager> factory =
            new IniSecurityManagerFactory("classpath:shiro.ini");
    //2、得到SecurityManager实例并绑定给SecurityUtils
    org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
    SecurityUtils.setSecurityManager(securityManager);
    //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
    Subject subject = SecurityUtils.getSubject();
        long  timeout =  subject.getSession().getTimeout();
        System.out.println("系统设置的超时是："+getGapTime(timeout)+"分钟");
    UsernamePasswordToken token = new UsernamePasswordToken("zhang", "1263");
    try {
        try {
            Thread.sleep(180000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //4、登录，即身份验证
        subject.login(token);
    }catch (UnknownAccountException e){
        System.out.println("未知的账号");
    }
    catch (IncorrectCredentialsException e){
        System.out.println("密码错误!");
    }
    catch (AuthenticationException e) {
        System.out.println("异常："+e.getMessage());
        //5、身份验证失败
    }
    //   Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
    System.out.println("***********已经登录******");
    //6、退出
    subject.logout();
    System.out.println("**********退出********");
}


    @Test
    public void testHelloworldReamlTest() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2、得到SecurityManager实例并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("lisi", "123");
        long  timeout =  subject.getSession().getTimeout();
        System.out.println("系统设置的超时是："+getGapTime(timeout)+"分钟");
        SecurityUtils.getSubject().getSession().setTimeout(8000);
        long  timeout1 =  subject.getSession().getTimeout();
        System.out.println("系统设置的超时是："+getGapTime(timeout1)+"分钟");

        try {

            try {
                Thread.sleep(8000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //4、登录，即身份验证
            subject.login(token);
            System.out.println("***********登录成功******");

        }catch (UnknownAccountException e){
            System.out.println("LoginLogoutTest:未知的账号");
        }
        catch (IncorrectCredentialsException e){
            System.out.println("LoginLogoutTest:密码错误!");
        }

        catch (UnknownSessionException e){
            System.out.println("当前用户登录超时！！！");
            return;
        }

        catch (AuthenticationException e) {
            System.err.println("异常："+new PrintStackTrace().getStackTraceString(e));
            //5、身份验证失败
        }

        //   Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        //6、退出
        subject.logout();
        System.out.println("**********退出********");
    }


    /**
     * 毫秒转换分钟
     * @param time
     * @return
     */
    public String getGapTime(long time){
        long hours = time / (1000 * 60 * 60);
        long minutes = (time-hours*(1000 * 60 * 60 ))/(1000* 60);
        String diffTime="";
        if(minutes<10){
            diffTime=hours+":0"+minutes;
        }else{
            diffTime=hours+":"+minutes;
        }
        return diffTime;
    }
}
