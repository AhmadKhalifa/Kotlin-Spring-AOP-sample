package com.spring.aop

import com.spring.aop.nested.UserRepository
import org.springframework.stereotype.Repository

@Repository
open class AccountRepository {

    private lateinit var account: Account

    open fun addAccount(account: Account, userRepository: UserRepository) =
        println("Doing my DB WORK: ADDING AN ACCOUNT")

    open fun getAccount() = arrayOf(Account("Ahmed"), Account("Khalifa"))

    open fun setAccount(account: Account) {
        this.account = account
    }
}