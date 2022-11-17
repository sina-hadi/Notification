package com.codinginflow.notification

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.*
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codinginflow.notification.di.MainNotificationCompatBuilder
import com.codinginflow.notification.di.SecondNotificationCompatBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @MainNotificationCompatBuilder
    private val notificationBuilder: NotificationCompat.Builder,
    @SecondNotificationCompatBuilder
    private val notificationBuilder2 : NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat
) : ViewModel() {

    fun showSimpleNotification() {
//        notificationManager.notify(1, notificationBuilder
//            .setContentTitle("WELCOME")
//            .setContentText("Github channel : sina-hadi")
//            .setVisibility(VISIBILITY_PRIVATE)
//            .build()
//        )
        notificationManager.notify(1, notificationBuilder
            .setContentTitle("New Message")
            .build()
        )
    }

    fun cancelSimpleNotification() {
        notificationManager.cancel(1)
        notificationManager.cancel(2)
    }

    fun showProgress() {
        val max = 10
        var progress = 0
        viewModelScope.launch {
            while (progress != max) {
                delay(1000)
                progress += 1
                notificationManager.notify(
                    2,
                    notificationBuilder2
                        .setContentTitle("Downloading")
                        .setContentText("${progress}/${max}")
                        .setOngoing(true)
                        .setProgress(max, progress, false).build()
                )
            }
            notificationManager.notify(
                2,
                notificationBuilder2
                    .setContentTitle("Completed!")
                    .setContentText("")
                    .setOngoing(false)
                    .setProgress(0, 0, false).build()
            )
        }
    }

}