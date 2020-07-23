package craft.beer.posts.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "posts")
class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    //    @ManyToOne(fetch = FetchType.LAZY, )
    //    @JoinColumn(name="user_id")
    @Column(name = "user_id")
    var user: Int? = null

    @Column(name = "title", nullable = false)
    var title: String? = null

    @Column(name = "description", nullable = false)
    var description: String? = null

    @Column(name = "created_at", nullable = false)
    var createdAt: Date? = Date()

}