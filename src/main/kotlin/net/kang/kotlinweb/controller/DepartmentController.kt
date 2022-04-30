package net.kang.kotlinweb.controller

import net.kang.kotlinweb.domain.Department
import net.kang.kotlinweb.service.DepartmentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class DepartmentController (
    private var departmentService: DepartmentService
) {
    @GetMapping("/departments")
    fun findAll(): ResponseEntity<*> {
        return ResponseEntity.ok(departmentService.findAll())
    }

    @GetMapping("/department/{id}")
    fun findById(@PathVariable id: Long?): ResponseEntity<*> {
        val data = departmentService.findById(id!!)

        // Kotlin 에선 3항 연산자가 if (~~) TRUE 값 else FALSE 값 이렇게 된다.
        return if (id == null || data == null) {
            ResponseEntity<Void>(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity.ok(data)
        }
    }

    // 본래 데이터 추가 및 편집은 하나로 묶어서 하면 안 된다. 추가는 POST, 전체 필드에 대한 편집은 PUT, 일부 필드에 대한 편집은 PATCH 로 한다.
    @PostMapping("/department")
    fun save(@RequestBody department: Department?): ResponseEntity<*> {
        return if (department == null) {
            ResponseEntity<Void>(HttpStatus.BAD_REQUEST)
        } else {
            ResponseEntity.ok(departmentService.save(department!!))
        }
    }

    @DeleteMapping("/department/{id}")
    fun deleteById(@PathVariable id: Long?): ResponseEntity<*> {
        return if (id == null) {
            ResponseEntity<Void>(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity.ok(departmentService.deleteById(id!!))
        }
    }
}