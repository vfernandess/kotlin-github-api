package com.voidx.github.feature.user.detail.business

import com.voidx.github.data.network.vo.UserVO
import com.voidx.github.data.repository.UserDataSource
import com.voidx.github.feature.user.detail.UserDetailContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserDetailPresenter(
    var userDataSource: UserDataSource,
    var view: UserDetailContract.View): UserDetailContract.Presenter {

    private var disposable = CompositeDisposable()

    override fun load(user: String) {
        if (user.isEmpty() || user.trim().isEmpty()) {
            handleError()
            return
        }

        view.showLoading()

        val disposableDetail = userDataSource
            .getUser(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                lambda@{
                    handleSuccess(it)
                },
                lambda@{
                    handleError()
                }
            )
        disposable.add(disposableDetail)
    }

    private fun handleError() {
        view.hideLoading()
        view.hideDetails()
        view.showError()
    }

    private fun handleSuccess(user: UserVO) {
        view.hideLoading()
        view.hideError()

        view.showDetails()

        view.showDevInfos(user.followers, user.following, user.blog)
        view.showPersonInfos(user.name, user.location)

    }
}