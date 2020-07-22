package craft.beer.controllers.requests

data class NewPostRequest(
        var title: String,
        var description: String
)