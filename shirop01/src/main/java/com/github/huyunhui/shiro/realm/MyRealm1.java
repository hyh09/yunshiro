package com.github.huyunhui.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by dell on 2020/9/24.
 */
public class MyRealm1  implements Realm{




    public String getName() {
        return "myrealm1";
    }

    public boolean supports(AuthenticationToken token) {
        //UsernamePasswordToken 仅仅支持
        return token instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String  username = (String) token.getPrincipal();
        System.out.println("[myrealm1]用户的名字:"+username);
        String passworld = new String((char[]) token.getCredentials());
        System.out.println("[myrealm1]登录用户的密码："+passworld);
        if(!"zhangsan".equals(username)){
            System.out.println("[myrealm1]用户名错误");
            throw  new UnknownAccountException();
        }
        if(!"123".equals(passworld)){
            System.out.println("[myrealm1]密码错误:");
            throw  new IncorrectCredentialsException();
        }

        //如果身份验证成功了，就要返回AuthenticationInfo
        return  new SimpleAuthenticationInfo(username,passworld,getName());



       // return null;
    }
}
