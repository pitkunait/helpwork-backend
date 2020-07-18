package craft.beer.data.user.repositories

import craft.beer.data.user.entities.RoleEntity
import craft.beer.data.user.entities.Roles
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface RoleRepository : JpaRepository<RoleEntity, Long?> {
    fun findByName(name: Roles?): Optional<RoleEntity>
}