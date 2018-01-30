package biz.mesto.anaken.mvp.presenters.main

import biz.mesto.anaken.mvp.presenters.BasePresenter
import biz.mesto.anaken.mvp.views.LoginView
import javax.inject.Inject

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
class LoginPresenter @Inject constructor(): BasePresenter() {

    var view: LoginView? = null

    fun doLogin(username : String) {
        view?.onLoginSuccess(username)
    }
}