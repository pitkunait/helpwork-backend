package craft.beer.data.user

data class UserEntity(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)