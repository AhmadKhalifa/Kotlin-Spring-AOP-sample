package com.spring.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.omg.CORBA.Object
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Aspect
@Order(1)
@Component
open class AnalyticsAspect : BaseAspect() {

//    override fun beforeCallingMethod(joinPoint: JoinPoint) {
//        println()
//        println("[Analytics] Before executing ${joinPoint.signature.toShortString()}")
//    }
//
//    override fun afterCallingMethod(joinPoint: JoinPoint) {
//        println("[Analytics] After executing ${joinPoint.signature.toShortString()}")
//        println()
//    }
}