package com.spring.aop

import com.spring.aop.nested.UserRepository
import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main() {
    val context = AnnotationConfigApplicationContext(Configurations::class.java)
    val accountRepository = context.getBean(AccountRepository::class.java)
    val membershipRepository = context.getBean(MembershipRepository::class.java)
    val userRepository = context.getBean(UserRepository::class.java)
    accountRepository.addAccount(Account(""), userRepository)
    accountRepository.setAccount(Account(""))
    accountRepository.getAccount()
    membershipRepository.addAccount()
    try {
        userRepository.addUser()
    } catch (e: Exception) {
    }
    context.close()
}