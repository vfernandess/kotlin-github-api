package com.voidx.github.feature.user.list.business

import com.voidx.github.data.network.vo.UserVO
import com.voidx.github.data.repository.UserDataSource
import com.voidx.github.feature.user.list.UserListContract
import com.voidx.github.feature.user.list.view.ListUserAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserListPresenter(
    private val view: UserListContract.View,
    private val userDataSource: UserDataSource): UserListContract.Presenter, ListUserAdapter.ListUserDelegate {

    private var disposable = CompositeDisposable()
    private var users: List<UserVO>? = null

    override fun load() {
        view.showLoading()
        val disposableList = userDataSource
            .getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                lambda@{ users: List<UserVO> ->
                    this.users = users
                    view.hideLoading()
                    view.showUsers()
                },
                lambda@{
                    view.hideLoading()
                    view.hideUsers()
                    view.showError()
                }
            )
        disposable.add(disposableList)
    }

    override fun onItemSelected(position: Int) {
        users?.let {
            view.showUser(it[position].login)
        }
    }

    override fun destroy() {
        disposable.clear()
    }

    override fun getUserCount(): Int {
        return users?.count() ?: 0
    }

    override fun putValues(view: UserListContract.ItemView, position: Int) {
        users?.let {
            val user = it[position]
            view.putValues(user.name, user.avatar, user.login)
        }
    }

}