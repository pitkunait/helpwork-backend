package craft.beer.posts.repositories

import craft.beer.posts.model.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    fun findById(id: Int): Optional<Post>

    fun existsById(id: Int): Boolean

    @Transactional
    fun deleteById(id: Int)

    fun findByTitleContains(substring: String, pageable:Pageable): Page<Post>?

}