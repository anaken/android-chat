package biz.mesto.anaken.di

import biz.mesto.anaken.App
import biz.mesto.anaken.di.main.MainComponent
import biz.mesto.anaken.di.main.MainModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
@Component(modules = [AppModule::class, FirebaseModule::class])
@Singleton
interface AppComponent {
    fun addMainComponent(mainModule: MainModule) : MainComponent

    fun inject(it : App)
}