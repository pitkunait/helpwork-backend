package craft.beer.controllers.requests

data class SignInRequest(
        var username: String,
        var password: String
)