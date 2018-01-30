package biz.mesto.anaken.mvp.presenters

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
abstract class BasePresenter {

    open fun onStart() { }

    open fun onStop() { }

    open fun onDestroy() { }
}