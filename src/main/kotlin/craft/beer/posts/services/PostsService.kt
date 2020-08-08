package craft.beer.posts.services

import craft.beer.controllers.requests.NewPostRequest
import craft.beer.core.user.repositories.UserRepository
import craft.beer.posts.model.Post
import craft.beer.posts.repositories.PostRepository
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Slice
import org.springframework.data.domain.Sort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import javax.security.auth.message.AuthException

@Service
class PostsService(private val modelMapper: ModelMapper,
                   private val postRepository: PostRepository,
                   private val userRepository: UserRepository
) : IPostsService {

    override fun newPost(newPostRequest: NewPostRequest) {
        val username: String = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.findByUsername(username).get()
        val post: Post = modelMapper.map(newPostRequest, Post::class.java)
        post.user = user.id
        postRepository.save(post)
    }

    override fun listPosts(page: Int): Page<Post> {
        return postRepository.findAll(PageRequest.of(page, 10, Sort.by("createdAt").descending()))
    }

    override fun getOnePost(id: Int): Post? {
        return postRepository.findById(id).get()
    }

    override fun editPost(id: Int, newPostRequest: NewPostRequest) {
        val username: String = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.findByUsername(username).get()
        val post: Post = postRepository.findById(id).get()
        if (post.user == user.id) {
            modelMapper.map(newPostRequest, post)
            postRepository.save(post)
        } else {
            throw AuthException("Can not edit this post.")
        }
    }

    override fun deletePost(id: Int) {
        val username: String = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.findByUsername(username).get()
        val post: Post = postRepository.findById(id).get()
        if (post.user == user.id) {
            postRepository.deleteById(id)
        } else {
            throw AuthException("Can not delete this post.")
        }
    }

    override fun searchPosts(page: Int, title: String): Slice<Post> {
        val posts = postRepository.findByTitleContains(title, PageRequest.of(page, 10, Sort.by("createdAt").descending()))
        return posts!!
    }
}