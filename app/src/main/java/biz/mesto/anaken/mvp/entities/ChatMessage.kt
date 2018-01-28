package biz.mesto.anaken.mvp.entities

/**
 * Created by Yury Korobeynikov on 28.01.2018.
 */
data class ChatMessage(
        val username: String = "",
        val message: String = "",
        val time: Long = 0
)