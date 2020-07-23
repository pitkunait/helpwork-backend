package craft.beer.posts.services

import craft.beer.controllers.requests.NewPostRequest
import craft.beer.controllers.requests.SearchPostsRequest
import craft.beer.controllers.responses.ListPostsResponse
import craft.beer.controllers.responses.NewPostResponse
import craft.beer.core.user.repositories.UserRepository
import craft.beer.posts.model.Post
import craft.beer.posts.repositories.PostRepository
import org.modelmapper.ModelMapper
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class PostsService(val modelMapper: ModelMapper,
                   val postRepository: PostRepository,
                   val userRepository: UserRepository
) : IPostsService {

    override fun newPost(newPostRequest: NewPostRequest): NewPostResponse {
        val username: String = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.findByUsername(username).get()
        val post: Post = modelMapper.map(newPostRequest, Post::class.java)
        post.user = user.id
        postRepository.save(post)
        return NewPostResponse("Post created")
    }

    override fun searchPosts(searchPostsRequest: SearchPostsRequest): ListPostsResponse {
        val posts = postRepository.findByTitleContains(searchPostsRequest.title)
        return ListPostsResponse("Posts fetched", posts)
    }

    override fun listPosts(): ListPostsResponse {
        val posts: List<Post> = postRepository.findAll()
        return ListPostsResponse("Posts fetched", posts)
    }
}