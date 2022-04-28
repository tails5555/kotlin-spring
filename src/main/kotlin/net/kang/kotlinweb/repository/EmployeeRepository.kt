package net.kang.kotlinweb.repository

import net.kang.kotlinweb.domain.Employee
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository: CrudRepository<Employee, Long> {
}