package com.voidx.github.feature.user.list.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.facebook.shimmer.ShimmerFrameLayout
import com.voidx.github.R
import com.voidx.github.feature.user.list.UserListContract
import kotlinx.android.synthetic.main.fragment_list_user.*
import org.koin.android.scope.currentScope

class ListUserFragment : Fragment(), UserListContract.View {

    val adapter : ListUserAdapter by currentScope.inject()

    val presenter : UserListContract.Presenter by currentScope.inject()

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
        (this.loadingContainer as? ShimmerFrameLayout)?.let {
            it.visibility = VISIBLE
            it.startShimmer()
        }
    }

    override fun hideLoading() {
        (this.loadingContainer as? ShimmerFrameLayout)?.let {
            it.stopShimmer()
            it.visibility = GONE
        }
    }

    override fun showUsers() {
        this.users.visibility = VISIBLE
        adapter.notifyDataSetChanged()
    }

    override fun hideUsers() {
        this.users.visibility = GONE
    }

    override fun showUser(name: String) {
        //TODO call parent navigation to detail
    }

    override fun showError() {
        this.errorContainer.visibility = VISIBLE
    }

    override fun hideError() {
        this.errorContainer.visibility = GONE
    }

    override fun showEmptyError() {
        this.emptyContainer.visibility = VISIBLE
    }

    override fun hideEmpty() {
        this.emptyContainer.visibility = GONE
    }
}
