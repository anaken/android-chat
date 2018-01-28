package biz.mesto.anaken.mvp.routers

/**
 * Created by Yury Korobeynikov on 27.01.2018.
 */
interface MainRouter {

    fun goLogin()
    fun goChat(username: String)
}