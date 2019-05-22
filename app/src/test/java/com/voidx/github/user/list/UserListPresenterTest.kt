package com.voidx.github.user.list

import com.voidx.github.data.repository.UserDataSource
import com.voidx.github.feature.user.list.UserListContract
import com.voidx.github.feature.user.list.business.UserListPresenter
import com.voidx.github.user.list.UserListRobot.Companion.injectUserList
import com.voidx.github.utils.RxImmediateSchedulerRule
import io.mockk.every
import io.mockk.mockk
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
    private var userDataSource: UserDataSource = mockk(relaxed = true)

    @Before
    fun setup() {
        presenter = UserListPresenter(view, userDataSource)
    }

    @Test
    fun `load github users successfully`() {

        every { userDataSource.getUsers() } returns injectUserList()

        presenter.load()

        verifyAll {
            view.showLoading()
            view.hideLoading()
            view.showUsers()
        }

    }

}