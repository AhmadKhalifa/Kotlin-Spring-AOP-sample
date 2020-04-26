package com.spring.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.Signature
import org.aspectj.lang.annotation.*

abstract class BaseAspect {

    @Pointcut("execution(* *(..))")
    fun mainPackagePointcut() {}

    @Pointcut("execution(* java.lang.Object.toString())")
    fun toStringPointCut() {}

    @Pointcut("mainPackagePointcut() && !toStringPointCut()")
    fun allMethodsPointCut() {}

    @Around("allMethodsPointCut()")
    fun aroundAllMethods(proceedingJoinPoint: ProceedingJoinPoint) =
        aroundMethodCalling(proceedingJoinPoint)

    @Before("allMethodsPointCut())")
    fun beforeAllMethods(joinPoint: JoinPoint) =
        beforeCallingMethod(joinPoint)

    @After("allMethodsPointCut())")
    fun afterAllMethods(joinPoint: JoinPoint) =
        afterCallingMethod(joinPoint)

    @AfterReturning(pointcut = "allMethodsPointCut()", returning = "result")
    fun afterAllMethodsReturn(joinPoint: JoinPoint, result: Any?) =
        afterMethodReturn(joinPoint, result)

    @AfterThrowing(pointcut = "allMethodsPointCut()", throwing = "throwable")
    fun afterAllMethodsThrowingException(joinPoint: JoinPoint, throwable: Throwable) =
        afterThrowingException(joinPoint, throwable)

    // Abstract methods

    open fun aroundMethodCalling(proceedingJoinPoint: ProceedingJoinPoint): Any? = proceedingJoinPoint.proceed()

    open fun beforeCallingMethod(joinPoint: JoinPoint) {}

    open fun afterCallingMethod(joinPoint: JoinPoint) {}

    open fun afterMethodReturn(joinPoint: JoinPoint, result: Any?) {}

    open fun afterThrowingException(joinPoint: JoinPoint, throwable: Throwable) {}
}