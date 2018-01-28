package biz.mesto.anaken.di.main

import biz.mesto.anaken.mvp.routers.MainRouter
import dagger.Module
import dagger.Provides

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
@Module
class MainModule (private val router: MainRouter) {
    @Provides
    @MainScope
    fun provideMainRouter() : MainRouter = router
}