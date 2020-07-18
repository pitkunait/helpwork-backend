package craft.beer.data.user.entities

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = ["username", "email"])])
class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @NotBlank
    var username: String? = null

    @NotBlank
    @Column(name = "first_name")
    var firstName: String? = null

    @NotBlank
    @Column(name = "last_name")
    var lastName: String? = null

    @NotBlank
    @Email
    var email: String? = null

    @NotBlank
    var password: String? = null

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "roles",
//            joinColumns = [JoinColumn(name = "user_id")],
//            inverseJoinColumns = [JoinColumn(name = "role_id")]
//    )
//    var roles: Set<RoleEntity> = HashSet<RoleEntity>()

    constructor() {}
    constructor(username: String?,
                email: String?,
                password: String?,
                firstName: String?,
                lastName: String?) {
        this.username = username
        this.email = email
        this.password = password
        this.firstName = firstName
        this.lastName = lastName
    }

}