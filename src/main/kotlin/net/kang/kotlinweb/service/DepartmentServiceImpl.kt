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

    // T? 이렇게 써줘야 null 도 반환이 가능하다. T 만 쓰면 무조건 T 타입 객체를 반환 할 수 밖에 없다.
    override fun findById(id: Long): Department? {
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
        val res: Department? = departmentRepository.findById(id).orElse(null)
        if (res != null) {
            // ON DELETE SET NULL 할 때, 모든 사원들을 돌면서 department 값을 null 로 하는게 나을까?
            // 차라리 employees 에 있는 사원들의 department 값을 NULL 로 처리하여 UPDATE 하는게 더 낫지 않을까?
            res.employees.forEach { e -> e.department = null }
            departmentRepository.deleteById(id)
        }
        return mutableMapOf(
            "result" to (res != null)
        )
    }
}