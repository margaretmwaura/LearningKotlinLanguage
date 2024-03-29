package com.android.kotlinproject.Presenter

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.android.kotlinproject.R
import com.android.kotlinproject.View.MainActivity

/**
 * Helper class for showing and canceling new message
 * notifications.
 *
 *
 * This class makes heavy use of the [NotificationCompat.Builder] helper
 * class to create notifications in a backward-compatible way.
 */
object NewMessageNotification {
    /**
     * The unique identifier for this type of notification.
     */
    private val NOTIFICATION_TAG = "NewMessage"
    const val REMINDER_CHANENEL = "reminders"

    /**
     * Shows the notification, or updates a previously shown notification of
     * this type, with the given parameters.
     *
     *
     * TODO: Customize this method's arguments to present relevant content in
     * the notification.
     *
     *
     * TODO: Customize the contents of this method to tweak the behavior and
     * presentation of new message notifications. Make
     * sure to follow the
     * [
 * Notification design guidelines](https://developer.android.com/design/patterns/notifications.html) when doing so.
     *
     * @see .cancel
     */
    fun notify(
        context: Context,titleText : String,
        exampleString: String, number: Int
    ) {

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(context)
            .addNextIntentWithParentStack(intent)
            .getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT)

        val shareIntent : PendingIntent = PendingIntent.getActivity(context,0,
            Intent.createChooser(Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, exampleString),
            "Share the note"),
            PendingIntent.FLAG_UPDATE_CURRENT)
        val res = context.resources


        val builder = NotificationCompat.Builder(context,
            REMINDER_CHANENEL
        )

            // Set appropriate defaults for the notification light, sound,
            // and vibration.
            .setDefaults(Notification.DEFAULT_ALL)

            // Set required fields, including the small icon, the
            // notification title, and text.
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(titleText)
            .setContentText(exampleString)

            // All fields below this line are optional.

            // Use a default priority (recognized on devices running Android
            // 4.1 or later)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


            // Set ticker text (preview) information for this notification.
            .setTicker(titleText)

            // Show a number. This is useful when stacking notifications of
            // a single type.
            .setNumber(number)

            // If this notification relates to a past or upcoming event, you
            // should set the relevant time information using the setWhen
            // method below. If this call is omitted, the notification's
            // timestamp will by set to the time at which it was shown.
            // TODO: Call setWhen if this notification relates to a past or
            // upcoming event. The sole argument to this method should be
            // the notification timestamp in milliseconds.
            //.setWhen(...)

            // Set the pending intent to be initiated when the user touches
            // the notification.
            .setContentIntent(pendingIntent)

            .addAction(R.drawable.ic_launcher_background, "Shsre",shareIntent)
            // Automatically dismiss the notification when it is touched.
            .setAutoCancel(true)

        notify(context, builder.build())
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private fun notify(context: Context, notification: Notification) {
        val nm = context
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.notify(NOTIFICATION_TAG, 0, notification)
        } else {
            nm.notify(NOTIFICATION_TAG.hashCode(), notification)
        }
    }

    /**
     * Cancels any notifications of this type previously shown using
     * [.notify].
     */
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    fun cancel(context: Context) {
        val nm = context
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.cancel(NOTIFICATION_TAG, 0)
        } else {
            nm.cancel(NOTIFICATION_TAG.hashCode())
        }
    }
}
