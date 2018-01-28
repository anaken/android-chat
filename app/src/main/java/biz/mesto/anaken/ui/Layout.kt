package biz.mesto.anaken.ui

import android.support.annotation.LayoutRes

/**
 * Аннотация для указания лэйаута экрана или фрагмента
 * Created by Yury Korobeynikov on 27.01.2018.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Layout(@LayoutRes val id: Int)
