package biz.mesto.anaken.di.main.chat

import biz.mesto.anaken.ui.fragments.ChatFragment
import dagger.Subcomponent

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
@ChatScope
@Subcomponent(modules = [ChatModule::class])
interface ChatComponent {
    fun inject(chatFragment: ChatFragment)
}