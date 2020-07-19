package craft.beer.model

import javax.persistence.*
import javax.validation.constraints.Size


@Entity
@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = ["username", "email"])])
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    @Column(unique = true, nullable = false)
    var username: String? = null

    @Column(name = "first_name", nullable = false)
    var firstName: String? = null

    @Column(name = "last_name", nullable = false)
    var lastName: String? = null

    @Column(unique = true, nullable = false)
    var email: String? = null

    @Size(min = 4, message = "Minimum password length: 8 characters")
    var password: String? = null

    @ElementCollection(fetch = FetchType.LAZY, targetClass = Role::class)
    @JoinTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")])
    @Column(name = "role")
    var roles: Set<Role>? = setOf(Role.ROLE_USER)

}