package craft.beer.controllers.responses


data class RefreshTokenResponse(
        var message: String,
        var accessJwt: String? = null
)