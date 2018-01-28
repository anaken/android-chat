package biz.mesto.anaken.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import biz.mesto.anaken.R
import biz.mesto.anaken.mvp.entities.ChatMessage
import biz.mesto.anaken.mvp.views.ChatView
import org.joda.time.DateTime

/**
 * Created by Yury Korobeynikov on 28.01.2018.
 */
class ChatMessageAdapter(private val items: MutableList<ChatView.Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val TIME_FORMAT: String = "dd/MM/yyyy HH:mm"
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.adapter_chat_message, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as ViewHolder).bind(items[position])
    }

    fun setMessages(messages: List<ChatMessage?>?) {
        items.clear()
        messages
                ?.map {
                    it?.let {
                        ChatView.Message(
                                username = it.username,
                                message = it.message,
                                time = DateTime(it.time)
                        )
                    }
                }
                ?.forEach { it?.let { items.add(it) } }
        notifyItemInserted(items.size)
    }

    fun addMessage(message: ChatMessage) {
        val chatMessage = ChatView.Message(
                username = message.username,
                message = message.message,
                time = DateTime(message.time)
        )
        items.add(chatMessage)
        notifyItemInserted(items.size)
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bind(message : ChatView.Message) {
            val user: TextView = itemView.findViewById(R.id.adapter_chat__username)
            val text: TextView = itemView.findViewById(R.id.adapter_chat__text)
            val time: TextView = itemView.findViewById(R.id.adapter_chat__time)

            user.text = message.username
            text.text = message.message
            time.text = message.time.toString(TIME_FORMAT)
        }
    }
}