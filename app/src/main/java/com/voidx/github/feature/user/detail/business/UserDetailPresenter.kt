package com.voidx.github.feature.user.detail.business

import com.voidx.github.data.network.vo.UserVO
import com.voidx.github.data.repository.UserDataSource
import com.voidx.github.feature.user.detail.UserDetailContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserDetailPresenter(
    var userDataSource: UserDataSource,
    var view: UserDetailContract.View
) : UserDetailContract.Presenter {

    private var disposable = CompositeDisposable()

    override fun load(user: String) {
        if (user.isEmpty() || user.trim().isEmpty()) {
            handleError()
            return
        }

        view.hideDetails()
        view.hideError()
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

        view.showDevInfo(
            user.followers.toString(),
            user.following.toString(),
            user.repoCount.toString(),
            user.gistCount.toString()
        )
        view.showPersonInfo(user.name, user.login, user.avatar)

    }
}