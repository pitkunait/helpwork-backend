package craft.beer.controllers.responses

import craft.beer.core.user.model.Role
import craft.beer.posts.model.Post

data class UserInformationResponse(
        var username: String? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var email: String? = null,
        var posts: List<Post>? = null,
        var roles: Set<Role>? = null
)