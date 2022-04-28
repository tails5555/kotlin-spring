package net.kang.kotlinweb.controller

import net.kang.kotlinweb.domain.Department
import net.kang.kotlinweb.domain.Employee
import net.kang.kotlinweb.service.DepartmentService
import net.kang.kotlinweb.service.EmployeeService
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
        return ResponseEntity.ok(departmentService.findById(id!!))
    }

    @PostMapping("/department")
    fun save(@RequestBody department: Department?): ResponseEntity<*> {
        return ResponseEntity.ok(
            mutableMapOf("method" to departmentService.save(department!!))
        )
    }

    @DeleteMapping("/department/{id}")
    fun deleteById(@PathVariable id: Long?): ResponseEntity<*> {
        return ResponseEntity.ok(
            mutableMapOf("result" to departmentService.deleteById(id!!))
        )
    }
}