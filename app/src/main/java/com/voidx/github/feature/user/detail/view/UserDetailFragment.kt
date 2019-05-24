package com.voidx.github.feature.user.detail.view


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import com.voidx.github.R
import com.voidx.github.feature.Navigator
import com.voidx.github.feature.user.detail.UserDetailContract
import kotlinx.android.synthetic.main.fragment_user_detail.*
import org.koin.android.scope.currentScope

const val EXTRA_USER_NICK = "EXTRA_USER_NICK"

class UserDetailFragment : Fragment(), UserDetailContract.View {

    companion object {

        fun withArguments(nick: String): Bundle {
            return Bundle().apply {
                putString(EXTRA_USER_NICK, nick)
            }
        }
    }

    val presenter : UserDetailContract.Presenter? by currentScope.inject()

    var navigator: Navigator? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is Navigator) {
            navigator = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.toolbar.setNavigationOnClickListener { navigator?.onBackPressed() }

        arguments?.getString(EXTRA_USER_NICK)?.let {
            presenter?.load(it)
        }
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

    override fun showDetails() {
        detailContainer.visibility = VISIBLE
    }

    override fun hideDetails() {
        detailContainer.visibility = GONE
    }

    override fun showError() {
        errorContainer.visibility = VISIBLE
    }

    override fun hideError() {
        errorContainer.visibility = VISIBLE
    }

    override fun showPersonInfo(name: String, nick: String, avatar: String) {
        this.name.text = name
        this.nick.text = getString(R.string.nick_formatter, nick)
        Glide.with(this)
            .load(avatar)
            .placeholder(R.drawable.ic_profile)
            .circleCrop()
            .into(this.avatar)
    }

    override fun showDevInfo(followers: String, following: String, repoCount: String, gistCount: String) {
        this.followers.text = getString(R.string.followers_formatter, followers)
        this.following.text = getString(R.string.following_formatter, following)
        this.repos.text = getString(R.string.repo_formatter, repoCount)
        this.gists.text = getString(R.string.gist_formatter, gistCount)
    }

}
