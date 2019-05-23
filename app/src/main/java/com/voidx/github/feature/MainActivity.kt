package com.voidx.github.feature

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.voidx.github.R
import com.voidx.github.feature.user.list.view.ListUserFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val listUserFragment : ListUserFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.container, listUserFragment).commit()

    }
}
