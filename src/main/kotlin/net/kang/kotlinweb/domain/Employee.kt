package net.kang.kotlinweb.domain

import javax.persistence.*

// data class 는 toString, equals, hashCode 이런 것들을 자동으로 구현해준다.
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

    // Department ID 값이 삭제되게 하려면 ON DELETE NULL, ON DELETE CASCADE 를 확인해야 한다.
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = true)
    var department: Department?
) : BaseEntity()