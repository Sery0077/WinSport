package sery.vlasenko.winsport.ui.splash

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.*
import sery.vlasenko.winsport.R
import sery.vlasenko.winsport.utils.CurrentUser

class FragmentSplash : Fragment(R.layout.splash_layout) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSplash(view)
    }

    private fun setSplash(view: View) {
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)

        CoroutineScope(Dispatchers.IO).launch {

            val sp = requireActivity().getSharedPreferences(
                getString(R.string.auth_prefs),
                Context.MODE_PRIVATE
            )

            val mName = sp.getString(getString(R.string.auth_name), null) ?: ""
            val mAge = sp.getInt(getString(R.string.auth_height), 0)
            val mWeight = sp.getInt(getString(R.string.auth_weight), 0)
            val mProgress = sp.getInt(getString(R.string.auth_progress), 0)

            CurrentUser.run {
                name = mName
                age = mAge
                weight = mWeight
                progress.postValue(mProgress)
            }

            delay(500)
            progressBar.progress = 25
            delay(1000)
            progressBar.progress = 75
            delay(1200)
            progressBar.progress = 100
            delay(300)

            withContext(Dispatchers.Main) {
                if (mName == "" && mAge == 0 && mWeight == 0 && mProgress == 0) {
                    findNavController().navigate(R.id.action_splashFragment_to_authFragment)
                    requireActivity().window?.setBackgroundDrawableResource(R.drawable.back_auth)
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_menuFragment)
                    requireActivity().window?.setBackgroundDrawableResource(R.drawable.back_main)
                }
            }
        }
    }
}