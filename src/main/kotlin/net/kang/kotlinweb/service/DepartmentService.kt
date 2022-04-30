package net.kang.kotlinweb.service

import net.kang.kotlinweb.domain.Department

interface DepartmentService {
    fun findAll(): List<Department>
    fun findById(id: Long): Department?
    fun save(employee: Department): MutableMap<String, *>
    fun deleteById(id: Long): MutableMap<String, *>
}