package biz.mesto.anaken.ui.activities

import android.os.Bundle
import biz.mesto.anaken.R
import biz.mesto.anaken.di.main.MainComponent
import biz.mesto.anaken.di.main.MainModule
import biz.mesto.anaken.mvp.routers.MainRouter
import biz.mesto.anaken.ui.Layout
import biz.mesto.anaken.ui.fragments.BackPressListener
import biz.mesto.anaken.ui.fragments.ChatFragment
import biz.mesto.anaken.ui.fragments.LoginFragment

@Layout(id = R.layout.activity_main)
class MainActivity : BaseActivity(), MainRouter {

    var mainComponent: MainComponent? = null

    var username: String? = null

    companion object {
        private const val FRAGMENT_PLACEHOLDER = R.id.a_main__placeholder
        private const val KEY_USERNAME = "username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (username == null) {
            goLogin()
        }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(FRAGMENT_PLACEHOLDER)
        if (fragment != null && fragment is BackPressListener && (fragment as BackPressListener).onBackPressed()) {
            return
        }

        super.onBackPressed()
    }

    override fun onInject() {
        mainComponent = getAppComponent().addMainComponent(MainModule(this))
        mainComponent?.inject(this)
    }

    override fun goLogin() {
        username = null
        goFragment(FRAGMENT_PLACEHOLDER, LoginFragment.newInstance())
    }

    override fun goChat(username: String) {
        this.username = username
        goFragment(FRAGMENT_PLACEHOLDER, ChatFragment.newInstance(username))
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(KEY_USERNAME, username)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        username = savedInstanceState?.getString(KEY_USERNAME)
    }
}
