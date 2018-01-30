package biz.mesto.anaken.ui.fragments

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import biz.mesto.anaken.R
import biz.mesto.anaken.di.main.login.LoginComponent
import biz.mesto.anaken.di.main.login.LoginModule
import biz.mesto.anaken.mvp.presenters.main.LoginPresenter
import biz.mesto.anaken.mvp.views.LoginView
import biz.mesto.anaken.ui.Layout
import biz.mesto.anaken.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
@Layout(id = R.layout.fragment_login)
class LoginFragment : BaseFragment<LoginPresenter>(), LoginView {

    private var loginComponent : LoginComponent? = null

    private var router : LoginRouter? = null

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    interface LoginRouter {
        fun goChat(username: String)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is LoginRouter) {
            router = context
        }
    }

    override fun onInitViews(state: Bundle?) {
        presenter.view = this
        f_login__btn_login.setOnClickListener{
            val username = f_login__name.text.toString()
            if (username.isNotEmpty()) {
                presenter.doLogin(username)
            }
        }
    }

    override fun onLoginSuccess(username : String) {
        Toast.makeText(activity, "Добро пожаловать $username!", Toast.LENGTH_SHORT).show()
        goChat(username)
    }

    override fun goChat(username: String) {
        router?.goChat(username)
    }

    override fun onInject() {
        loginComponent = (activity as MainActivity).mainComponent
                ?.addLoginComponent(LoginModule(this))
        loginComponent?.inject(this)
    }
}