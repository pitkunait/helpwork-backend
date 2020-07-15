package craft.beer.data.user

import org.springframework.data.repository.CrudRepository
import java.util.*

class UserRepository : IUserRepository, CrudRepository<UserEntity, Long> {
    override fun <S : UserEntity?> save(entity: S): S {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableIterable<UserEntity> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Long) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<UserEntity>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun <S : UserEntity?> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<Long>): MutableIterable<UserEntity> {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long): Optional<UserEntity> {
        TODO("Not yet implemented")
    }

    override fun delete(entity: UserEntity) {
        TODO("Not yet implemented")
    }
}