package com.example.querydsltransformbug;

import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class QuerydslTransformBugApplication implements CommandLineRunner {
    private final ParentService parentService;

    public QuerydslTransformBugApplication(ParentService parentService) {
        this.parentService = parentService;
    }

    public static void main(String[] args) {
        SpringApplication.run(QuerydslTransformBugApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        parentService.create();

        // no error
        Map<Long, Parent> map1 = parentService.getWithoutFetchJoin(1L);
        log.info("DATA : {}", map1.get(1L).getName());

        // error
        Map<Long, Parent> map2 = parentService.getWithFetchJoin(1L);
        log.info("DATA : {}", map2.get(1L).getName());
        /*
java.lang.IllegalStateException: Failed to execute CommandLineRunner
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:780) ~[spring-boot-2.6.7.jar:2.6.7]
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:761) ~[spring-boot-2.6.7.jar:2.6.7]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:310) ~[spring-boot-2.6.7.jar:2.6.7]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1312) ~[spring-boot-2.6.7.jar:2.6.7]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1301) ~[spring-boot-2.6.7.jar:2.6.7]
	at com.example.querydsltransformbug.QuerydslTransformBugApplication.main(QuerydslTransformBugApplication.java:26) ~[main/:na]
Caused by: java.lang.ClassCastException: class [Ljava.lang.Object; cannot be cast to class com.querydsl.core.Tuple ([Ljava.lang.Object; is in module java.base of loader 'bootstrap'; com.querydsl.core.Tuple is in unnamed module of loader 'app')
	at com.querydsl.core.group.GroupByMap.transform(GroupByMap.java:57) ~[querydsl-core-5.0.0.jar:na]
	at com.querydsl.core.group.GroupByMap.transform(GroupByMap.java:35) ~[querydsl-core-5.0.0.jar:na]
	at com.querydsl.core.support.FetchableQueryBase.transform(FetchableQueryBase.java:55) ~[querydsl-core-5.0.0.jar:na]
	at com.example.querydsltransformbug.ParentService.getWithFetchJoin(ParentService.java:45) ~[main/:na]
	at com.example.querydsltransformbug.ParentService$$FastClassBySpringCGLIB$$a913a3eb.invoke(<generated>) ~[main/:na]
	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218) ~[spring-core-5.3.19.jar:5.3.19]
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:793) ~[spring-aop-5.3.19.jar:5.3.19]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163) ~[spring-aop-5.3.19.jar:5.3.19]
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763) ~[spring-aop-5.3.19.jar:5.3.19]
	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:137) ~[spring-tx-5.3.19.jar:5.3.19]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.3.19.jar:5.3.19]
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763) ~[spring-aop-5.3.19.jar:5.3.19]
	at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:123) ~[spring-tx-5.3.19.jar:5.3.19]
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:388) ~[spring-tx-5.3.19.jar:5.3.19]
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119) ~[spring-tx-5.3.19.jar:5.3.19]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.3.19.jar:5.3.19]
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763) ~[spring-aop-5.3.19.jar:5.3.19]
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:708) ~[spring-aop-5.3.19.jar:5.3.19]
	at com.example.querydsltransformbug.ParentService$$EnhancerBySpringCGLIB$$82705e06.getWithFetchJoin(<generated>) ~[main/:na]
	at com.example.querydsltransformbug.QuerydslTransformBugApplication.run(QuerydslTransformBugApplication.java:36) ~[main/:na]
	at com.example.querydsltransformbug.QuerydslTransformBugApplication$$FastClassBySpringCGLIB$$123f3653.invoke(<generated>) ~[main/:na]
	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218) ~[spring-core-5.3.19.jar:5.3.19]
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:793) ~[spring-aop-5.3.19.jar:5.3.19]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163) ~[spring-aop-5.3.19.jar:5.3.19]
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763) ~[spring-aop-5.3.19.jar:5.3.19]
	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:137) ~[spring-tx-5.3.19.jar:5.3.19]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.3.19.jar:5.3.19]
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763) ~[spring-aop-5.3.19.jar:5.3.19]
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:708) ~[spring-aop-5.3.19.jar:5.3.19]
	at com.example.querydsltransformbug.QuerydslTransformBugApplication$$EnhancerBySpringCGLIB$$f479204c.run(<generated>) ~[main/:na]
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:777) ~[spring-boot-2.6.7.jar:2.6.7]
	... 5 common frames omitted
         */
    }
}
