package com.example.posts_sdk.gateway.network.mapping

import com.example.posts_sdk.domain.response.PostUiModel
import com.example.posts_sdk.gateway.network.response.Post
import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class PostsMapperSpek : Spek({
    Feature("PostsMapper") {
        val postsMapper by memoized { PostsMapper() }

        Scenario("Mapping post") {
            lateinit var postUiModel: PostUiModel

            When("map") {
                postUiModel = postsMapper.toUiPost(Post(userId, title, body, userId))
            }

            Then("maps correctly"){
                val expectedPostUiModel = PostUiModel(userId, postId, title, body)
                assertEquals(expectedPostUiModel, postUiModel)
            }
        }

        Scenario("Mapping posts") {
            lateinit var postUiModels: List<PostUiModel>

            When("map") {
                postUiModels = postsMapper.toUiPosts(mutableListOf(Post(userId, title, body, userId)))
            }

            Then("maps correctly"){
                val expectedUIModels = mutableListOf(PostUiModel(userId, postId, title, body))
                assertEquals(expectedUIModels, postUiModels)
            }
        }
    }
})

private val title = "title"
private val body = "body"
private val userId = 0
private val postId = 0