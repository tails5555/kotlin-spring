package net.kang.kotlinweb.service

import net.kang.kotlinweb.domain.Employee
import org.springframework.stereotype.Service

@Service
interface EmployeeService {
    fun findAll(): List<Employee>
    fun findById(id: Long): Employee
    fun save(employee: Employee): MutableMap<String, *>
    fun deleteById(id: Long): MutableMap<String, *>
}