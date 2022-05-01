package net.kang.kotlinweb.controller

import com.fasterxml.jackson.databind.ObjectMapper
import net.kang.kotlinweb.domain.Department
import net.kang.kotlinweb.service.DepartmentServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(DepartmentController::class)
@ExtendWith(SpringExtension::class)
class DepartmentControllerTest {
    @MockBean
    private lateinit var departmentService: DepartmentServiceImpl

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `부서 목록 정보를 가져오다` () {
        var mapper: ObjectMapper = ObjectMapper()
        var result: MutableList<Department> = mutableListOf()
        result.add(Department(1L, "NAME", "PHONE"))

        given(departmentService.findAll()).willReturn(result)

        mockMvc
            .perform(get("/departments"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(mapper.writeValueAsString(result)))
    }

    @Test
    fun `부서 단일 정보를 가져오다` () {
        var mapper: ObjectMapper = ObjectMapper()
        var result: Department = Department(1L, "NAME", "PHONE")

        given(departmentService.findById(1L)).willReturn(result)

        mockMvc
            .perform(get("/department/1"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(mapper.writeValueAsString(result)))
    }

    @Test
    fun `부서 정보를 삽입한다` () {
        var mapper: ObjectMapper = ObjectMapper()
        var department: Department = Department(1L, "NAME", "PHONE")
        var result: MutableMap<String, *> = mutableMapOf(
            "message" to "CREATED",
            "data" to department
        )

        given(departmentService.save(department)).willReturn(result)

        mockMvc
            .perform(
                post("/department")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(department))
            )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(mapper.writeValueAsString(result)))
    }

    @Test
    fun `부서 정보를 수정한다` () {
        var mapper: ObjectMapper = ObjectMapper()
        var department: Department = Department(1L, "NAME", "PHONE")
        var result: MutableMap<String, *> = mutableMapOf(
            "message" to "MODIFIED",
            "data" to department
        )

        given(departmentService.save(department)).willReturn(result)

        mockMvc
            .perform(
                post("/department")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(department))
            )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(mapper.writeValueAsString(result)))
    }

    @Test
    fun `부서 정보 저장 중 유효하지 않는 값을 넣는다` () {
        var mapper: ObjectMapper = ObjectMapper()
        mockMvc
            .perform(
                post("/department")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(null))
            )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `부서 정보를 삭제한다` () {
        var mapper: ObjectMapper = ObjectMapper()
        var result: MutableMap<String, *> = mutableMapOf(
            "result" to true
        )

        given(departmentService.deleteById(1L)).willReturn(result)

        mockMvc
            .perform(delete("/department/1"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(mapper.writeValueAsString(result)))
    }
}