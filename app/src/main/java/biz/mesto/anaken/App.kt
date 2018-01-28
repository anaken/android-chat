package biz.mesto.anaken

import android.support.multidex.MultiDexApplication
import biz.mesto.anaken.di.AppComponent
import biz.mesto.anaken.di.AppModule
import biz.mesto.anaken.di.DaggerAppComponent

/**
 * Created by Yury Korobeynikov on 26.01.2018.
 */
class App : MultiDexApplication() {

    /**
     * Компонент графа зависимостей
     */
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}