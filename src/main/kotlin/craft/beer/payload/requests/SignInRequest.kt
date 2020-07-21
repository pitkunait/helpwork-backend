package craft.beer.payload.requests

data class SignInRequest(
        var username: String,
        var password: String
)