package net.kang.kotlinweb.service

import net.kang.kotlinweb.domain.Employee
import net.kang.kotlinweb.repository.EmployeeRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import java.util.stream.StreamSupport

@Service
class EmployeeServiceImpl (
    private var employeeRepository: EmployeeRepository
): EmployeeService {
    override fun findAll(): List<Employee> {
        return StreamSupport
                .stream(employeeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList())
    }

    override fun findById(id: Long): Employee? {
        return employeeRepository.findById(id).orElse(null)
    }

    override fun save(employee: Employee): MutableMap<String, *> {
        val msg: String = if (employeeRepository.existsById(employee.id)) "MODIFIED" else "CREATED"
        return mutableMapOf(
            "message" to msg,
            "data" to employeeRepository.save(employee)
        )
    }

    override fun deleteById(id: Long): MutableMap<String, *> {
        val res: Boolean = employeeRepository.existsById(id)
        if (res) {
            employeeRepository.deleteById(id)
        }
        return mutableMapOf(
            "result" to res
        )
    }
}