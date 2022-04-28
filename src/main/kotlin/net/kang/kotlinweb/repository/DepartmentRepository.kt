package net.kang.kotlinweb.repository

import net.kang.kotlinweb.domain.Department
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DepartmentRepository: CrudRepository<Department, Long> {
}