package com.spring.aop.aspect

import com.spring.aop.LoggingAspect
import org.aspectj.lang.ProceedingJoinPoint
import java.util.logging.Logger

open class BaseAspect {

    private val logger: Logger = Logger.getLogger(LoggingAspect::class.java.name)

    open fun aroundMethodCalling(proceedingJoinPoint: ProceedingJoinPoint): Any? = with(proceedingJoinPoint) {
        onExecutingMethod(proceedingJoinPoint)
        val startTime = System.currentTimeMillis()
        var result: Any? = null
        try {
            result = proceed()
            onMethodExecuted(result)
        } catch (throwable: Throwable) {
           onExceptionThrown(proceedingJoinPoint, throwable)
        }
        val executionTime = System.currentTimeMillis() - startTime
        onMethodReturn(proceedingJoinPoint, executionTime, result)
        result
    }

    protected open fun onExecutingMethod(proceedingJoinPoint: ProceedingJoinPoint) =
        logger.info("Executing method ${proceedingJoinPoint.signature.toShortString()} " +
                "with parameters: [${proceedingJoinPoint.args.joinToString(", ")}]")

    protected open fun onMethodExecuted(result: Any?) {

    }

    protected open fun onExceptionThrown(proceedingJoinPoint: ProceedingJoinPoint, throwable: Throwable) {
        val methodName = proceedingJoinPoint.signature.toShortString()
        val methodInfo = methodName.split(".")
        val sourceClass = methodInfo[0]
        val sourceMethod = methodInfo[1]
        logger.throwing(sourceClass, sourceMethod, throwable)
        logger.warning(
            "Method ${proceedingJoinPoint.signature.toShortString()} " +
                    "threw ${throwable.javaClass.simpleName}: ${throwable.message}\n\tat " +
                    throwable.stackTrace.joinToString("\n\tat ", transform = StackTraceElement::toString)
        )
    }

    protected open fun onMethodReturn(proceedingJoinPoint: ProceedingJoinPoint, executionTime: Long, result: Any?) {
        logger.info(
            "Method ${proceedingJoinPoint.signature.toShortString()} executed in $executionTime milliseconds" +
                    if (proceedingJoinPoint.signature.toString().startsWith("void")) ""
                    else ", and returned: " + when (result) {
                        is Collection<*> -> "[${result.joinToString(", ")}]"
                        is Array<*> -> "[${result.toList().joinToString(", ")}]"
                        else -> "$result"
                    }
        )
    }
}