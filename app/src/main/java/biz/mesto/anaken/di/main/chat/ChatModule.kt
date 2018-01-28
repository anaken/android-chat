package biz.mesto.anaken.di.main.chat

import biz.mesto.anaken.mvp.views.ChatView
import dagger.Module
import dagger.Provides

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
@Module
class ChatModule (private val view: ChatView) {
    @ChatScope
    @Provides
    fun provideChatView() : ChatView = view
}