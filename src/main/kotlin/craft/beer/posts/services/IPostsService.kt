package craft.beer.posts.services

import craft.beer.controllers.requests.NewPostRequest
import craft.beer.controllers.responses.ListPostsResponse
import craft.beer.controllers.responses.NewPostResponse

interface IPostsService {
    fun listPosts(): ListPostsResponse

    fun newPost(newPostRequest: NewPostRequest): NewPostResponse
}