package biz.mesto.anaken.mvp.presenters.main

import biz.mesto.anaken.mvp.entities.ChatMessage
import biz.mesto.anaken.mvp.presenters.BasePresenter
import biz.mesto.anaken.mvp.views.ChatView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.FirebaseDatabase
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import javax.inject.Inject

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
class ChatPresenter @Inject constructor(
        private val firebaseDatabase: FirebaseDatabase,
        private val firebaseAuth: FirebaseAuth
) : BasePresenter() {

    companion object {
        const val CHAT_SECRET : String = "WKt73HdS9pkyUU"
        const val DEFAULT_CHAT : String = "chat"
    }

    var view: ChatView? = null

    lateinit var username: String
    private var flat: String? = DEFAULT_CHAT

    fun init(username: String, flat: String? = DEFAULT_CHAT) {
        this.username = username
        this.flat = if (flat.isNullOrEmpty()) DEFAULT_CHAT else flat
    }

    override fun onStart() {
        firebaseDatabase.reference
                .child( flat )
                .addChildEventListener( view )
    }

    fun sendMessage(message: String) {
        val chatMessage = ChatMessage(
                username = username,
                message = message,
                time = DateTime.now(DateTimeZone.getDefault()).millis
        )
        firebaseDatabase.reference
                .child( flat + "/" + CHAT_SECRET )
                .push()
                .setValue(chatMessage)
    }

    override fun onDestroy() {
        firebaseDatabase.reference
                .child( flat )
                .removeEventListener( view as ChildEventListener )
    }
}