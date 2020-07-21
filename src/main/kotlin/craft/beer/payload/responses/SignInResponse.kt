package craft.beer.payload.responses

data class SignInResponse(
        var message: String,
        var accessJwt: String? = null,
        var refreshJwt: String? = null
)