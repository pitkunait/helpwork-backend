package craft.beer.controllers.responses

data class SignUpResponse(
        var message: String,
        var accessJwt: String? = null,
        var refreshJwt: String? = null
)