package net.kang.kotlinweb.service

import net.kang.kotlinweb.domain.Department
import net.kang.kotlinweb.domain.Employee

interface DepartmentService {
    fun findAll(): List<Department>
    fun findById(id: Long): Department
    fun save(employee: Department): MutableMap<String, *>
    fun deleteById(id: Long): MutableMap<String, *>
}