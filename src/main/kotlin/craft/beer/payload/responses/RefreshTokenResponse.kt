package craft.beer.payload.responses


data class RefreshTokenResponse(
        val message: String,
        val accessJwt: String? = null
)