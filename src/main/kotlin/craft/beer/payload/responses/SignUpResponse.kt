package craft.beer.payload.responses

data class SignUpResponse(
        val message: String,
        val jwtToken: String?
)