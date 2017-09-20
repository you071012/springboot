package com.ukar.aspect;

import com.ukar.annotation.AnnotationDemo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by jyou on 2017/9/18.
 */
@Component
@Aspect
@Order(-1)//对于存在事物时配置，可以让该切点保证在事物执行之前执行
public class AspectTest {

    /**
     * 定义切点方法，和切入哪些地方
     */
    @Pointcut("execution(* com.ukar.aspect.*.*(..))")
    public void pointCut(){
        System.out.println("切点执行.......................");
    }

    /**
     * 进入方法前执行
     */
    @Before("pointCut()")
    public void beforeMethod(){
        System.out.println("前置通知。。。");
    }

    /**
     *进入方法前和方法结执行完毕后执行，pointCut()执行的切点，
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCut()")
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("环绕通知上..............");
        Object obj=pjp.proceed();
        System.out.println("环绕通知下..............");
        return obj;
    }

    /**
     * 注解类型切点,遇到该注解时会执行切面，切入方法
     * @param pjp
     * @param annotationDemo
     * @return
     * @throws Throwable
     */
    @Around("@annotation(annotationDemo)")
    public Object aroundMethod2(ProceedingJoinPoint pjp, AnnotationDemo annotationDemo ) throws Throwable{
        System.out.println("注解环绕通知上..............");
        Object obj=pjp.proceed();
        System.out.println("注解环绕通知下..............");
        return obj;
    }

    /**
     * 方法执行完毕后执行，无论是否出现异常
     */
    @After("pointCut()")
    public void afterMethod(){
        System.out.println("后置通知.............");
    }

    /**
     * 方法执行完毕后执行，出现异常不执行
     */
    @AfterReturning("pointCut()")
    public void afterReturning(){
        System.out.println("return结束后通知.............");
    }

    /**
     * 方法出现异常后执行
     */
    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("异常通知..........");
    }
}
