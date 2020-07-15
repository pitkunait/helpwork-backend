package craft.beer.data.user

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    var password: String
)