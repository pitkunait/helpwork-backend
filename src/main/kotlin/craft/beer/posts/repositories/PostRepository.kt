package craft.beer.posts.repositories

import craft.beer.posts.model.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    fun findById(id: Int): Optional<Post>

    fun existsById(id: Int): Boolean

    fun findByTitleContains(substring: String): List<Post>?

}