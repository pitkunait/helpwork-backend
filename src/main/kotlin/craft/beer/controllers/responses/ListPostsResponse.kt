package craft.beer.controllers.responses

import craft.beer.posts.model.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Slice

data class ListPostsResponse(
        var message: String,
        var posts: Slice<Post>? = null
)