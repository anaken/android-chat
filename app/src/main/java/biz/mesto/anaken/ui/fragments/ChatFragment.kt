package biz.mesto.anaken.ui.fragments

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import biz.mesto.anaken.R
import biz.mesto.anaken.di.main.chat.ChatComponent
import biz.mesto.anaken.di.main.chat.ChatModule
import biz.mesto.anaken.mvp.entities.ChatMessage
import biz.mesto.anaken.mvp.presenters.main.ChatPresenter
import biz.mesto.anaken.mvp.views.ChatView
import biz.mesto.anaken.ui.Layout
import biz.mesto.anaken.ui.activities.MainActivity
import biz.mesto.anaken.ui.adapters.ChatMessageAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import kotlinx.android.synthetic.main.fragment_chat.*

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
@Layout(id = R.layout.fragment_chat)
class ChatFragment: BaseFragment<ChatPresenter>(), ChatView, BackPressListener {

    private var chatComponent: ChatComponent? = null

    private var messagesAdapter: ChatMessageAdapter? = null

    lateinit var username: String

    companion object {
        fun newInstance(username: String): ChatFragment {
            val it = ChatFragment()
            it.username = username
            return it
        }
    }

    override fun onInitViews() {
        Log.d("TAG", "INITED CHAT")
        messagesAdapter = ChatMessageAdapter(mutableListOf())
        f_chat__messages.layoutManager = LinearLayoutManager(activity!!.application,
                LinearLayoutManager.VERTICAL, false)
        f_chat__messages.adapter = messagesAdapter

        f_chat__btn_send.setOnClickListener{
            presenter.sendMessage(username, f_chat__text.text.toString())
            f_chat__text.text.clear()
        }
    }

    override fun onInject() {
        chatComponent = (activity as MainActivity).mainComponent
                ?.addChatComponent(ChatModule(this))
        chatComponent?.inject(this)
    }

    private fun scrollToBottom() {
        f_chat__messages.scrollToPosition(messagesAdapter!!.itemCount - 1)
    }

    override fun onCancelled(p0: DatabaseError?) { Log.d("ChatFragment", "onCancelled") }

    override fun onChildMoved(p0: DataSnapshot?, p1: String?) { Log.d("ChatFragment", "onChildMoved") }

    override fun onChildChanged(dataSnapshot: DataSnapshot?, s: String?) {
        Log.d("ChatFragment", "onChildChanged")
        val setMessages = dataSnapshot?.children
                ?.map { it.getValue<ChatMessage>(ChatMessage::class.java) }
        messagesAdapter?.setMessages(setMessages)
        scrollToBottom()
    }

    override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
        Log.d("ChatFragment", "onChildAdded")
        dataSnapshot.children
                .map { it.getValue<ChatMessage>(ChatMessage::class.java) }
                .forEach { chatMessage -> chatMessage.let { messagesAdapter?.addMessage(it!!) } }
        scrollToBottom()
    }

    override fun onChildRemoved(p0: DataSnapshot?) { }

    override fun onBackPressed(): Boolean {
        presenter.goLogin()
        return true
    }
}