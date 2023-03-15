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
            val mAge = sp.getInt(getString(R.string.auth_height), -1)
            val mWeight = sp.getInt(getString(R.string.auth_weight), -1)

            CurrentUser.run {
                name = mName
                age = mAge
                weight = mWeight
            }

//            delay(500)
            progressBar.progress = 25
//            delay(1000)
            progressBar.progress = 75
//            delay(1200)
            progressBar.progress = 100
//            delay(300)

            withContext(Dispatchers.Main) {
                if (mName == "" && mAge == -1 && mWeight == -1) {
                    findNavController().navigate(R.id.action_splashFragment_to_authFragment)
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_menuFragment)

                }

                requireActivity().window?.setBackgroundDrawableResource(R.drawable.back_1)
            }
        }
    }
}