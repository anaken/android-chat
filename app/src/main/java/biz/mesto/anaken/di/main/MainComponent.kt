package biz.mesto.anaken.di.main

import biz.mesto.anaken.di.main.chat.ChatComponent
import biz.mesto.anaken.di.main.chat.ChatModule
import biz.mesto.anaken.di.main.login.LoginComponent
import biz.mesto.anaken.di.main.login.LoginModule
import biz.mesto.anaken.ui.activities.MainActivity
import dagger.Subcomponent

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun addLoginComponent(loginModule: LoginModule) : LoginComponent
    fun addChatComponent(chatModule: ChatModule) : ChatComponent

    fun inject(mainActivity: MainActivity)
}
