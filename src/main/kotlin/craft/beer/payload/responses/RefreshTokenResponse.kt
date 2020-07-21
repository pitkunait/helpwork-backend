package craft.beer.payload.responses


data class RefreshTokenResponse(
        var message: String,
        var accessJwt: String? = null
)