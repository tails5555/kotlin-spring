package net.kang.kotlinweb.domain

import javax.persistence.*

@Entity
@Table(name = "_employee")
data class Employee (
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(name = "first_name", length = 30)
    val firstName: String,

    @Column(name = "last_name", length = 30)
    val lastName: String,

    @Column(name = "email", length = 60)
    val email: String,

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = true) // 원래 이거 안 써도 되긴 하지만, 테이블의 초기 생성 테스트를 위해 쓰는 것이다.
    val department: Department
) : BaseEntity()