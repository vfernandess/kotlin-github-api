package com.voidx.github.user.list

import com.voidx.github.data.repository.UserDataSource
import com.voidx.github.feature.user.list.UserListContract
import com.voidx.github.feature.user.list.business.UserListPresenter
import com.voidx.github.user.list.UserListObjects.Companion.injectEmpty
import com.voidx.github.user.list.UserListObjects.Companion.injectError
import com.voidx.github.user.list.UserListObjects.Companion.injectUserList
import com.voidx.github.utils.RxImmediateSchedulerRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserListPresenterTest {

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var presenter: UserListContract.Presenter

    private var view: UserListContract.View = mockk(relaxed = true)
    private var itemView: UserListContract.ItemView = mockk(relaxed = true)
    private var userDataSource: UserDataSource = mockk(relaxed = true)

    @Before
    fun setup() {
        presenter = UserListPresenter(view, userDataSource)
    }

    @Test
    fun `show github users successfully`() {

        every { userDataSource.getUsers() } returns injectUserList()

        presenter.load()

        verifyAll {
            view.hideError()
            view.hideEmpty()
            view.hideUsers()
            view.showLoading()
            view.hideLoading()
            view.showUsers()
            view.hideEmpty()
        }
    }

    @Test
    fun `show error successfully`() {

        every { userDataSource.getUsers() } returns injectError()

        presenter.load()

        verifyAll {
            view.hideError()
            view.hideEmpty()
            view.hideUsers()
            view.showLoading()
            view.hideLoading()
            view.showError()
        }
    }

    @Test
    fun `show empty error successfully`() {

        every { userDataSource.getUsers() } returns injectEmpty()

        presenter.load()

        verifyAll {
            view.hideError()
            view.hideEmpty()
            view.hideUsers()
            view.showLoading()
            view.hideLoading()
            view.hideUsers()
            view.showEmptyError()
        }
    }

    @Test
    fun `show user item successfully`() {
        every { userDataSource.getUsers() } returns injectUserList()

        presenter.load()
        presenter.putValues(itemView, 0)

        verifyAll {
            view.hideError()
            view.hideEmpty()
            view.hideUsers()
            view.showLoading()
            view.hideLoading()
            view.showUsers()
            view.hideEmpty()
        }

        verify {
            itemView.putValues(any(), any())
        }
    }

    @Test
    fun `show user detail successfully`() {
        every { userDataSource.getUsers() } returns injectUserList()

        presenter.load()
        presenter.putValues(itemView, 0)
        presenter.onItemSelected(0)

        verifyAll {
            view.hideError()
            view.hideEmpty()
            view.hideUsers()
            view.showLoading()
            view.hideLoading()
            view.showUsers()
            view.hideEmpty()
            itemView.putValues(any(), any())
            view.showUser(any())
        }
    }

}