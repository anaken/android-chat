package biz.mesto.anaken.ui.activities

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import biz.mesto.anaken.App
import biz.mesto.anaken.di.AppComponent
import biz.mesto.anaken.ui.Layout

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * Вызывает метод внедрения зависимостей
     */
    protected abstract fun onInject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Установка лэйаута из аннотации
        val cls = javaClass
        if (!cls.isAnnotationPresent(Layout::class.java)) {
            return
        }
        setContentView((cls.getAnnotation(Layout::class.java) as Layout).id)

        onInject()
    }

    /**
     * Выполняет переход к фрагменту
     */
    protected fun goFragment(@IdRes placeholder: Int, fragment: Fragment) {
        val tx = supportFragmentManager.beginTransaction()
        tx.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
        tx.replace(placeholder, fragment, fragment.javaClass.canonicalName)
        tx.commit()
    }

    protected fun getAppComponent() : AppComponent {
        return (application as App).component
    }
}