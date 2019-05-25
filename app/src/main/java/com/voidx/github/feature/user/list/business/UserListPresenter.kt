package com.voidx.github.feature.user.list.business

import com.voidx.github.data.network.vo.UserVO
import com.voidx.github.data.repository.UserDataSource
import com.voidx.github.feature.user.list.UserListContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserListPresenter(
    private val view: UserListContract.View,
    private val userDataSource: UserDataSource
) : UserListContract.Presenter {

    private var disposable = CompositeDisposable()
    private var users: List<UserVO>? = null

    override fun load() {
        view.hideError()
        view.hideEmpty()
        view.hideUsers()
        view.showLoading()

        val disposableList = userDataSource
            .getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                lambda@ {
                    handleSuccess(it)
                },
                lambda@{
                    handleError(it)
                })

        disposable.add(disposableList)
    }

    override fun onItemSelected(position: Int) {
        users?.let {
            view.showUser(it[position].login)
        }
    }

    override fun destroy() {
        disposable.clear()
        users = null
    }

    override fun getUserCount(): Int {
        return users?.count() ?: 0
    }

    override fun putValues(view: UserListContract.ItemView, position: Int) {
        users?.let {
            val user = it[position]
            view.putValues(user.avatar, user.login)
        }
    }

    private fun handleSuccess(users: List<UserVO>) {
        view.hideLoading()

        if (users.isEmpty()) {
            view.hideUsers()
            view.showEmptyError()
            return
        }

        this.users = users
        view.showUsers()
        view.hideEmpty()
    }

    private fun handleError(error: Throwable) {
        view.hideLoading()
        view.showError()
    }

}