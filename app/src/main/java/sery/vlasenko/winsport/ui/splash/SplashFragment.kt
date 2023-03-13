package sery.vlasenko.winsport.ui.splash

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.*
import sery.vlasenko.winsport.R

class SplashFragment : Fragment(R.layout.splash_layout) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)

        CoroutineScope(Dispatchers.IO).launch {
            delay(500)
            progressBar.progress = 25
            delay(1000)
            progressBar.progress = 75
            delay(1200)
            progressBar.progress = 100
            delay(300)

            withContext(Dispatchers.Main) {
                findNavController().navigate(R.id.action_splashFragment_to_authFragment)
            }
        }
    }
}