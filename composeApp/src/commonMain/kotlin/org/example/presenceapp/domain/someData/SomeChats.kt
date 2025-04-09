package org.example.presenceapp.domain.someData

class SomeChats(
    val someChats: List<Pair<String, String>> = listOf(
        "Chat1" to "Last message...",
        "Chat2" to "Another last message...",
        "Chat3" to "The latest message...",
        "Chat4" to "The very latest message...",
        "Chat5" to "The veeeeeery latest message...",
        "123" to "Last message...",
        "321" to "Another last message...",
        "213" to "The latest message...",
        "312" to "The very latest message...",
        "132" to "The veeeeeery latest message...",
        "231" to "The really veeeeeery latest message...",
    )
)

data class Message(
    val text: String,
    val isFromUser: Boolean
)