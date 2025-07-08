package com.tenyun7.aspects;

import com.tenyun7.annotation.VerifySign;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Primary
public class SignAspect {

    @Pointcut("@annotation(com.tenyun7.annotation.VerifySign)")
    public void signPointcut() {
        // Do nothing because of pointcut
    }

    @Before("signPointcut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();

        VerifySign autoAnnotation = AnnotationUtils.findAnnotation(method, VerifySign.class);
        if (autoAnnotation == null) {
            autoAnnotation = AnnotationUtils.findAnnotation(joinPoint.getTarget().getClass(), VerifySign.class);
        }
        Objects.requireNonNull(autoAnnotation);

        Class<?> clazz = autoAnnotation.value();
    }

}
