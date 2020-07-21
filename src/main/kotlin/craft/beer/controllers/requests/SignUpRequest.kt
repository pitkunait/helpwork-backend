package craft.beer.controllers.requests

data class SignUpRequest(
        var username: String,
        var email: String,
        var firstName: String,
        var lastName: String,
        var password: String
)