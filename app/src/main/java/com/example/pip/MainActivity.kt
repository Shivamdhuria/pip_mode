package com.example.pip

import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Bundle
import android.util.Rational
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            enterPipMode()
        }
    }


    override fun onUserLeaveHint() {
        enterPipMode()
    }

    private fun enterPipMode() {
        val actions: ArrayList<RemoteAction> = ArrayList()
        val remoteAction = RemoteAction(
            Icon.createWithResource(this@MainActivity, android.R.drawable.ic_menu_info_details),
            "Info","Info Details",
            PendingIntent.getActivity(
                this@MainActivity, 0, Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")), 0
            )
        )
        actions.add(remoteAction)
        val aspectRatio = Rational(16, 9)
        val params = PictureInPictureParams
            .Builder()
            .setAspectRatio(aspectRatio)
            .setActions(actions)
            .build()
        enterPictureInPictureMode(params)
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean, newConfig: Configuration) {
        if (isInPictureInPictureMode) {
            //hide all unimportant views
            image.visibility = GONE
            button.visibility = GONE
            heading.visibility = GONE
            description.visibility = GONE
        } else {
            //hide all unimportant views
            image.visibility = VISIBLE
            button.visibility = VISIBLE
            heading.visibility = VISIBLE
            description.visibility = VISIBLE
        }
    }
}