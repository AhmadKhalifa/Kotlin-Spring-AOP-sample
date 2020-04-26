package com.spring.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Aspect
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Aspect
@Order(2)
@Component
class LoggingAspect : BaseAspect() {

    private val logger: Logger = Logger.getLogger(LoggingAspect::class.java.name)

    override fun aroundMethodCalling(proceedingJoinPoint: ProceedingJoinPoint): Any? = with(proceedingJoinPoint) {
        val methodName = signature.toShortString()

        logger.info("Executing method $methodName with parameters: [${args.joinToString(", ")}]")

        val startTime = System.currentTimeMillis()
        var result: Any? = null
        try {
            result = proceed()
        } catch (throwable: Throwable) {
            val methodInfo = methodName.split(".")
            val sourceClass = methodInfo[0]
            val sourceMethod = methodInfo[1]
            logger.throwing(sourceClass, sourceMethod, throwable)
            logger.warning(
                "Method ${signature.toShortString()} " +
                        "threw ${throwable.javaClass.simpleName}: ${throwable.message}\n\tat " +
                        throwable.stackTrace.joinToString("\n\tat ", transform = StackTraceElement::toString)
            )
        }
        val endTime = System.currentTimeMillis()
        val executionTime = endTime - startTime
        logger.info(
            "Method $methodName executed in $executionTime milliseconds" +
                    if (signature.toString().startsWith("void")) ""
                    else ", and returned: " + when (result) {
                        is Collection<*> -> "[${result.joinToString(", ")}]"
                        is Array<*> -> "[${result.toList().joinToString(", ")}]"
                        else -> "$result"
                    }
        )
        result
    }

//    override fun beforeCallingMethod(joinPoint: JoinPoint) {
//        println("[Logging] Before executing ${joinPoint.signature.toShortString()} with parameters: " +
//                "[${joinPoint.args.joinToString(", ")}]")
//    }

//    override fun afterCallingMethod(joinPoint: JoinPoint) {
//        println("[Logging] After executing ${joinPoint.signature.toShortString()}")
//    }

//    override fun afterMethodReturn(joinPoint: JoinPoint, result: Any?) {
//        if (joinPoint.signature.isVoid()) {
//            println(
//                "Method ${joinPoint.signature.toShortString()} returned " +
//                        when (result) {
//                            is Collection<*> -> "[${result.joinToString(", ")}]"
//                            is Array<*> -> "[${result.toList().joinToString(", ")}]"
//                            else -> "$result"
//                        }
//            )
//        }
//    }

//    override fun afterThrowingException(joinPoint: JoinPoint, throwable: Throwable) {
//        println(
//            "Method ${joinPoint.signature.toShortString()} " +
//                    "threw ${throwable.javaClass.simpleName}: ${throwable.message}\n\tat " +
//                    throwable.stackTrace.joinToString("\n\tat ", transform = StackTraceElement::toString)
//        )
//    }
}