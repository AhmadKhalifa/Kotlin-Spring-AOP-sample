package com.spring.aop.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class ControllerAspect : BaseAspect() {

    companion object {

        const val PACKAGE = "com.spring.aop.controller"
    }

    @Around("execution(* $PACKAGE.*.*(..))")
    override fun aroundMethodCalling(proceedingJoinPoint: ProceedingJoinPoint): Any? {
        return super.aroundMethodCalling(proceedingJoinPoint)
    }
}