package net.kang.kotlinweb.service

import net.kang.kotlinweb.domain.Department
import net.kang.kotlinweb.repository.DepartmentRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import java.util.stream.StreamSupport

@Service
class DepartmentServiceImpl (
    var departmentRepository: DepartmentRepository
): DepartmentService {
    override fun findAll(): List<Department> {
        return StreamSupport
            .stream(departmentRepository.findAll().spliterator(), false)
            .collect(Collectors.toList())
    }

    override fun findById(id: Long): Department {
        return departmentRepository.findById(id).orElse(null)
    }

    override fun save(department: Department): MutableMap<String, *> {
        val msg: String = if (departmentRepository.existsById(department.id)) "MODIFIED" else "CREATED"
        return mutableMapOf(
            "message" to msg,
            "data" to departmentRepository.save(department)
        )
    }

    override fun deleteById(id: Long): MutableMap<String, *> {
        val res: Boolean = departmentRepository.existsById(id)
        if (res) {
            departmentRepository.deleteById(id)
        }
        return mutableMapOf(
            "result" to res
        )
    }
}