package craft.beer.controllers.payload.requests

data class SignInRequest(
        var username: String,
        var password: String
)