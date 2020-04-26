package com.spring.aop

import org.springframework.stereotype.Repository

@Repository
open class MembershipRepository {

    open fun addAccount() = println("Doing my DB WORK: ADDING A MEMBERSHIP")
}