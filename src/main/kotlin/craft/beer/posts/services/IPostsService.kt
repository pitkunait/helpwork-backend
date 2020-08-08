package craft.beer.posts.services

import craft.beer.controllers.requests.NewPostRequest
import craft.beer.posts.model.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Slice

interface IPostsService {
    fun newPost(newPostRequest: NewPostRequest)

    fun listPosts(page: Int): Page<Post>

    fun getOnePost(id: Int): Post?

    fun editPost(id: Int, newPostRequest: NewPostRequest)

    fun deletePost(id: Int)

    fun searchPosts(page: Int, title: String): Slice<Post>?
}