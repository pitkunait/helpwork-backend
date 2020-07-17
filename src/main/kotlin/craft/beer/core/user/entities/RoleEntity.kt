package craft.beer.core.user.entities

import javax.persistence.*

@Entity
@Table(name = "roles")
class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private var name: Roles? = null

    constructor() {}
    constructor(name: Roles?) {
        this.name = name
    }

    fun getName(): Roles? {
        return name
    }

    fun setName(name: Roles?) {
        this.name = name
    }
}