package craft.beer.payload.responses

data class UserInformationResponse(
        var username: String? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var email: String? = null
)