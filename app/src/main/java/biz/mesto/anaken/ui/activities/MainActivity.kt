package biz.mesto.anaken.ui.activities

import android.os.Bundle
import biz.mesto.anaken.R
import biz.mesto.anaken.di.main.MainComponent
import biz.mesto.anaken.di.main.MainModule
import biz.mesto.anaken.ui.Layout
import biz.mesto.anaken.ui.fragments.BackPressListener
import biz.mesto.anaken.ui.fragments.ChatFragment
import biz.mesto.anaken.ui.fragments.LoginFragment

@Layout(id = R.layout.activity_main)
class MainActivity : BaseActivity(), LoginFragment.LoginRouter, ChatFragment.ChatRouter {

    var mainComponent: MainComponent? = null

    var username: String? = null
    var flat: String? = null

    companion object {
        private const val FRAGMENT_PLACEHOLDER = R.id.a_main__placeholder
        private const val KEY_USERNAME = "username"
        private const val KEY_FLAT = "flat"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        username = savedInstanceState?.getString(KEY_USERNAME)
        flat = savedInstanceState?.getString(KEY_FLAT)

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
        mainComponent = getAppComponent().addMainComponent(MainModule())
        mainComponent?.inject(this)
    }

    override fun goLogin() {
        username = null
        flat = null
        goFragment(FRAGMENT_PLACEHOLDER, LoginFragment.newInstance())
    }

    override fun goChat(username: String, flat : String?) {
        this.username = username
        this.flat = flat
        goFragment(FRAGMENT_PLACEHOLDER, ChatFragment.newInstance(username, flat))
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(KEY_USERNAME, username)
        outState?.putString(KEY_FLAT, flat)
        super.onSaveInstanceState(outState)
    }
}
