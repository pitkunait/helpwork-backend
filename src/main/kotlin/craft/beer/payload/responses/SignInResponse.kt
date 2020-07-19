package craft.beer.payload.responses

data class SignInResponse(
        val message: String,
        val accessJwt: String?
)