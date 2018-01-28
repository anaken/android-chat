package biz.mesto.anaken.di

import android.content.Context
import biz.mesto.anaken.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
@Module
class AppModule(val app : App) {

    @Provides
    @Singleton
    internal fun providesContext(): Context = app

    @Provides
    @Singleton
    internal fun providesApp(): App = app
}