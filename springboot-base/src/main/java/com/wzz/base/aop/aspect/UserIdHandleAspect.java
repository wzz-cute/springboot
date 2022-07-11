package com.wzz.base.aop.aspect;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzz.base.aop.an.UserIdTransformation;
import com.wzz.base.listener.entity.User;
import com.wzz.base.listener.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class UserIdHandleAspect {

    @Autowired
    private UserService userService;

    @Pointcut(value = "@annotation(com.wzz.base.aop.an.UserIdTransformation)")
    public void handle() {
    }

    @Around("handle()")
    public Object doBefore(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        UserIdTransformation annotation = method.getAnnotation(UserIdTransformation.class);
        String filed = annotation.filed();//获取注解值
        Class<?> type;

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        //获取参数列表
        Object[] args = pjp.getArgs();
        //获取参数名称
        String[] paramNames = ((CodeSignature) pjp.getSignature()).getParameterNames();

        //存入map中
        Map<String, Object> parameter = new HashMap<>();
        int index = 0;//定位参数位置
        for (int i = 0; i < paramNames.length; i++) {
            parameter.put(paramNames[i], args[i]);
            if (paramNames[i].equals(filed)) {
                index = i;
            }
        }

        //若存于参数列表处理
        if (Arrays.asList(paramNames).contains(UserIdTransformation.Constant.USER_ID)) {
            return parameterListHandle(pjp, userQueryWrapper, args, parameter, index);
        }

        //存于对象处理  获取map中的参数
        return extracted(pjp, filed, userQueryWrapper, args, parameter, index);
    }

    private Object extracted(ProceedingJoinPoint pjp, String filed, QueryWrapper<User> userQueryWrapper, Object[] args, Map<String, Object> parameter, int index) throws Throwable {
        Class<?> type;
        Object o = parameter.get(filed);
        type = o.getClass();

        //反射根据名称获取字段
        Field declaredField = type.getDeclaredField(UserIdTransformation.Constant.USER_ID);
        declaredField.setAccessible(true);//因为是private修饰的

        //declaredField.get(o)  0是object的类型，字段根据Object获取
        if (ObjectUtil.isEmpty(declaredField.get(o))) {
            return pjp.proceed(args);
        }
        userQueryWrapper.eq("cof_emp_id", declaredField.get(o));
//        User one = userService.getOne(userQueryWrapper);
        User one = null;//模拟业务查询
        if (one == null) {
            //查无此人
        }

        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);//因为是private修饰的
            if (field.getName().equals(UserIdTransformation.Constant.USER_ID)) {
                field.set(o, one.getId());
                break;
            }
        }
        args[index] = o;
        return pjp.proceed(args);
    }

    private Object parameterListHandle(ProceedingJoinPoint pjp, QueryWrapper<User> userQueryWrapper, Object[] args, Map<String, Object> parameter, int index) throws Throwable {
        userQueryWrapper.eq("cof_emp_id", parameter.get(UserIdTransformation.Constant.USER_ID));
//        User one = userService.getOne(userQueryWrapper);
        User one = null;//模拟业务查询
        if (one == null) {
            //查无此人
        }
        args[index] = one.getId();
        return pjp.proceed(args);
    }


}
