package sery.vlasenko.winsport.ui.analytics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import sery.vlasenko.winsport.databinding.FragmentAnalyticsBinding
import sery.vlasenko.winsport.utils.CurrentUser

class FragmentAnalytics: Fragment() {

    private lateinit var binding: FragmentAnalyticsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnalyticsBinding.inflate(inflater)

        CurrentUser.progress.observe(viewLifecycleOwner) {
            binding.run {
                twProgressSum.text = "$it\u00A0"
                progressBar.progress = it / 25_000 * 100
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnInsert.setOnClickListener {
            val distance = binding.etDistance.text?.toString()?.toIntOrNull()
            val squats = binding.etSquats.text?.toString()?.toIntOrNull()

            require(distance != null && squats != null) { return@setOnClickListener }

            val progress = ((distance * 1000) + (squats * 10)) * CurrentUser.weight / 10

            CurrentUser.progress.value = progress
        }
        super.onViewCreated(view, savedInstanceState)
    }
}