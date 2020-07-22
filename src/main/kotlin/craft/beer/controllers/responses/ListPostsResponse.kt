package craft.beer.controllers.responses

import craft.beer.posts.model.Post

data class ListPostsResponse(
        var message: String,
        var posts: List<Post>? = null
)