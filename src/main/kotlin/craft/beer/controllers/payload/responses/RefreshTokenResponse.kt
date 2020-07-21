package craft.beer.controllers.payload.responses


data class RefreshTokenResponse(
        var message: String,
        var accessJwt: String? = null
)