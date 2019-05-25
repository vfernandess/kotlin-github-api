package com.voidx.github.feature.user.detail.view

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.voidx.github.R
import kotlinx.android.synthetic.main.user_photo_viewer_dialog.*

const val EXTRA_USER_PHOTO = "EXTRA_USER_PHOTO"

class UserPhotoViewerDialogFragment : DialogFragment() {

    companion object {

        fun newInstance(photo: String): UserPhotoViewerDialogFragment {
            val photoViewer = UserPhotoViewerDialogFragment()

            photoViewer.setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen)

            val arguments = Bundle()
            arguments.putString(EXTRA_USER_PHOTO, photo)
            photoViewer.arguments = arguments

            return photoViewer
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.user_photo_viewer_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(EXTRA_USER_PHOTO).let {
            if (it == null) {
                avatarViewer.setImageResource(R.drawable.ic_profile)
                context?.let { context ->
                    avatarViewer.setColorFilter(ContextCompat.getColor(context, R.color.whte), PorterDuff.Mode.SRC_IN)
                }
            } else {
                Glide.with(this)
                    .load(it)
                    .placeholder(R.drawable.ic_profile)
                    .circleCrop()
                    .into(avatarViewer)
            }
        }

        this.avatarViewer.setOnClickListener {
            dismiss()
        }

    }

    override fun onResume() {
        val params = dialog.window!!.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog.window!!.attributes = params
        super.onResume()
    }

}