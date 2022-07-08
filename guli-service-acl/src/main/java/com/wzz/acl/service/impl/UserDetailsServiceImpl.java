package com.wzz.acl.service.impl;

import com.wzz.acl.entity.User;
import com.wzz.acl.service.PermissionService;
import com.wzz.acl.service.UserService;
import com.wzz.security.entity.SecurityUser;
import com.wzz.security.security.DefaultPasswordEncoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 自定义userDetailsService - 认证用户详情
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Configuration
@Service("userDetailsService")
//public class UserDetailsServiceImpl implements UserDetailsService, AuthenticationProvider {
public class UserDetailsServiceImpl implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    private DefaultPasswordEncoder passwordEncoder;
    public UserDetailsServiceImpl(DefaultPasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }


    /***
     * 根据账号获取用户信息
     * @param :
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 从数据库中取出用户信息
//        User user = userService.selectByUsername(username);
//
//        // 判断用户是否存在
//        if (null == user) {
//            throw new UsernameNotFoundException("用户名不存在！");
//        }
//        // 返回UserDetails实现类
//        com.wzz.security.entity.User curUser = new com.wzz.security.entity.User();
//        BeanUtils.copyProperties(user, curUser);
//
//        List<String> authorities = permissionService.selectPermissionValueByUserId(user.getId());
//        SecurityUser securityUser = new SecurityUser(curUser);
//        securityUser.setPermissionValueList(authorities);
//        return securityUser;
//    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();     //表单提交的用户名
        String presentedPassword = (String) authentication.getCredentials();     //表单提交的密码

        User sysUser = userService.selectByUsername(username); // 根据用户名获取用户信息

        if (StringUtils.isEmpty(sysUser)) {
            throw new BadCredentialsException("用户名不存在");
        } else {
            com.wzz.security.entity.User curUser = new com.wzz.security.entity.User();
            BeanUtils.copyProperties(sysUser, curUser);

            if (authentication.getCredentials() == null) {
                throw new BadCredentialsException("凭证为空");
            } else if (!passwordEncoder.matches(presentedPassword, sysUser.getPassword())) {
                System.out.println("encodedPassword:" + presentedPassword);
                System.out.println("password:" + sysUser.getPassword());
                throw new BadCredentialsException("密码错误");
            } else {
                UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(curUser, authentication.getCredentials(), getUserAuthority(sysUser.getId()));
                result.setDetails(authentication.getDetails());
                return result;
            }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    //获取用户权限
    public List<GrantedAuthority> getUserAuthority(String userId) {

        // 角色(ROLE_admin)、菜单操作权限 sys:user:list
        List<String> authority = permissionService.selectPermissionValueByUserId(userId);// ROLE_admin,ROLE_normal,sys:user:list,....

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authority.stream().collect(Collectors.joining(",")));
        return grantedAuthorities;
    }
}
