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

    var view: ChatView? = null

    fun sendMessage(username: String, message: String) {
        val chatMessage = ChatMessage(
                username = username,
                message = message,
                time = DateTime.now(DateTimeZone.getDefault()).millis
        )
        firebaseDatabase.reference
                .child("chat")
                .push()
                .setValue(chatMessage)
    }

    override fun onDestroy() {
        firebaseDatabase.reference.removeEventListener( view as ChildEventListener )
    }
}