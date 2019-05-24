package com.voidx.github.user.detail

import com.voidx.github.data.repository.UserDataSource
import com.voidx.github.feature.user.detail.UserDetailContract
import com.voidx.github.feature.user.detail.business.UserDetailPresenter
import com.voidx.github.user.detail.UserDetailObjects.Companion.injectError
import com.voidx.github.user.detail.UserDetailObjects.Companion.injectUser
import com.voidx.github.utils.RxImmediateSchedulerRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyAll
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserDetailPresenterTest {

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var presenter: UserDetailContract.Presenter

    private var view: UserDetailContract.View = mockk(relaxed = true)
    private var userDataSource: UserDataSource = mockk(relaxed = true)

    @Before
    fun setup() {
        presenter = UserDetailPresenter(userDataSource, view)
    }

    @Test
    fun `show user detail correctly`() {
        every { userDataSource.getUser("johndoe") } returns injectUser()

        presenter.load("johndoe")

        verifyAll {
            view.showLoading()
            view.hideLoading()
            view.hideError()
            view.showDetails()
            view.showDevInfos(any(), any(), any())
            view.showPersonInfos(any(), any())
        }
    }

    @Test
    fun `show error successfully`() {

        every { userDataSource.getUser("johndoe") } returns injectError()

        presenter.load("johndoe")

        verifyAll {
            view.showLoading()
            view.hideLoading()
            view.hideDetails()
            view.showError()
        }
    }

    @Test
    fun `show error for invalid user`() {

        every { userDataSource.getUser(any()) } returns injectError()

        presenter.load("         ")

        verifyAll {
            view.hideLoading()
            view.hideDetails()
            view.showError()
        }
    }

}