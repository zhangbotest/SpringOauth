package com.bobo.cn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.stereotype.Component;

/**
 * 认证服务器配置类
 * 作用：配置哪些客户端可以访问当前配置服务器
 */
@Component
@EnableAuthorizationServer //标记为认证服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 配置可以访问此认证服务器的客户端信息
     * 可以采用：JDBC或者内存的方式（原理其实差不太多）
     * 为了方便暂时使用基于内存的形式存储客户端信息
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()//基于内存的模式
                .withClient("client")//客户端ID
                .secret(passwordEncoder.encode("123456"))//客户端秘钥，这里一定要加密。SpringSecurity 5.0之后规定密码必须加密。
                .resourceIds("product")//能访问的资源ID
                .scopes("user")//能访问的范围
                //授权方式：即当前认证服务器支持的授权方式分贝为 授权码模式、密码模式、客户端模式、简易模式、刷新Token
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                .autoApprove(false)//是否自动授权，一般设置为false表示需要用户手动授权
                .redirectUris("www.baidu.com");//重定向路径
    }

}