package com.example.jetchat.conversation

import androidx.compose.runtime.toMutableStateList
import com.example.jetchat.R

class ConversationUiState(
    val channelName: String, val channelMembers: Int, initialMessage: List<Message>
) {
    private val _messages: MutableList<Message> = initialMessage.toMutableStateList()
    val message: List<Message> = _messages

    fun addMessage(msg: Message) {
        _messages.add(0, msg)
    }
}

data class Message(
    val author: String,
    val content: String,
    val timestamp: String,
    val image: Int? = null,
    val authorImage: Int = if (author == "me") R.drawable.ali else R.drawable.someone_else
)