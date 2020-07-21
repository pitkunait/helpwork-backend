package craft.beer.controllers.responses

data class SignInResponse(
        var message: String,
        var accessJwt: String? = null,
        var refreshJwt: String? = null
)