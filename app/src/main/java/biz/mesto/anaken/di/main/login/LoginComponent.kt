package biz.mesto.anaken.di.main.login

import biz.mesto.anaken.ui.fragments.LoginFragment
import dagger.Subcomponent

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
@LoginScope
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {
    fun inject(loginFragment: LoginFragment)
}