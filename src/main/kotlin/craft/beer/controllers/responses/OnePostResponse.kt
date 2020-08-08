package craft.beer.controllers.responses

import craft.beer.posts.model.Post

data class OnePostResponse(
        var message: String,
        var post: Post? = null
)