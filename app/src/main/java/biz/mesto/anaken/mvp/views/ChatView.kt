package biz.mesto.anaken.mvp.views

import com.google.firebase.database.ChildEventListener
import org.joda.time.DateTime

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
interface ChatView : ChildEventListener {

    class Message(
            val username : String,
            val message : String,
            val time : DateTime
    )
}