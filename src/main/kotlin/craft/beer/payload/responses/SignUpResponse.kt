package craft.beer.payload.responses

data class SignUpResponse(
        val message: String,
        val accessJwt: String? = null,
        val refreshJwt: String? = null
)