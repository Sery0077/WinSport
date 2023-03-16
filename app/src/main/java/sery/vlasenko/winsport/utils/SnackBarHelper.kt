package sery.vlasenko.winsport.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import sery.vlasenko.winsport.R

object SnackBarHelper {
    fun errorSnackBar(
        view: View,
        anchorView: Int? = null,
        clickListener: View.OnClickListener
    ): Snackbar {
        val text = view.context.getString(R.string.snackbar_error)
        val actionText = view.context.getString(R.string.snackbar_action)

        val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE)
            .setAction(actionText, clickListener)

        anchorView?.let {
            snackBar.setAnchorView(it)
        }

        return snackBar
    }
}
