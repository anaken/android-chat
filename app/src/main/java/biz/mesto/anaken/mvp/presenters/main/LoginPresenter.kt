package biz.mesto.anaken.mvp.presenters.main

import biz.mesto.anaken.mvp.presenters.BasePresenter
import biz.mesto.anaken.mvp.routers.MainRouter
import biz.mesto.anaken.mvp.views.LoginView
import javax.inject.Inject

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
class LoginPresenter @Inject constructor(
        private val view: LoginView,
        private val router: MainRouter
) : BasePresenter() {

    override fun onStart() {

    }

    override fun onStop() {

    }

    fun doLogin(username : String) {
        view.onLoginSuccess(username)
        router.goChat(username)
    }
}