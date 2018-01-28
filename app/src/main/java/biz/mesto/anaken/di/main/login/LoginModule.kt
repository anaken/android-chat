package biz.mesto.anaken.di.main.login

import biz.mesto.anaken.mvp.views.LoginView
import dagger.Module
import dagger.Provides

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
@Module
class LoginModule (private val view: LoginView) {
    @LoginScope
    @Provides
    fun provideLoginView() : LoginView = view
}