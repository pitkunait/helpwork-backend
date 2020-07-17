package craft.beer.data.user.repositories

import craft.beer.data.user.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): Optional<UserEntity>

    fun existsByUsername(username: String): Boolean

    fun existsByEmail(email: String): Boolean
}