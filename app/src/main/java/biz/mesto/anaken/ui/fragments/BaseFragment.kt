package biz.mesto.anaken.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import biz.mesto.anaken.App
import biz.mesto.anaken.di.AppComponent
import biz.mesto.anaken.mvp.presenters.BasePresenter
import biz.mesto.anaken.ui.Layout
import javax.inject.Inject

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
abstract class BaseFragment<T : BasePresenter> : Fragment() {

    @Inject
    protected lateinit var presenter: T

    /**
     * Вызывает метод внедрения зависимостей
     */
    protected abstract fun onInject()

    /**
     * Вызывает метод настройки отображения
     */
    protected abstract fun onInitViews()

    /**
     * Возвращает компонент графа зависимостей
     */
    protected fun getAppComponent(): AppComponent {
        return (activity!!.application as App).component
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Установка лэйаута из аннотации
        val cls = javaClass
        if (!cls.isAnnotationPresent(Layout::class.java)) {
            return null
        }
        val annotation = cls.getAnnotation(Layout::class.java)
        val layout = annotation as Layout

        return inflater.inflate(layout.id, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onInitViews()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        onInject()
    }

    override fun onStart() {
        super.onStart()

        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()

        presenter.onStop()
    }
}