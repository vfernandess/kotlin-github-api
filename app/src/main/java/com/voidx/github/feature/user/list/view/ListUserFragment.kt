package com.voidx.github.feature.user.list.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.voidx.github.R
import com.voidx.github.feature.user.list.UserListContract
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import kotlinx.android.synthetic.main.fragment_list_user.*

class ListUserFragment : Fragment(), UserListContract.View {

    val presenter : UserListContract.Presenter by inject { parametersOf(this) }

    val adapter: ListUserAdapter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.users.adapter = this.adapter
    }

    override fun onStart() {
        super.onStart()
        presenter.load()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showUsers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideUsers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showUser(name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEmptyError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideEmpty() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
