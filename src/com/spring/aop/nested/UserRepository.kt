package com.spring.aop.nested

import org.springframework.stereotype.Repository
import sun.plugin.dom.exception.InvalidStateException

@Repository
open class UserRepository {

    open fun addUser(): Unit = throw InvalidStateException("Eeeeh yasta mosh keda")
}