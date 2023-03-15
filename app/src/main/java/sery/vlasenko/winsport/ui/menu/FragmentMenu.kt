package sery.vlasenko.winsport.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import sery.vlasenko.winsport.R
import sery.vlasenko.winsport.databinding.FragmentMenuBinding

class FragmentMenu: Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setClickers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setClickers() {
        binding.btnAnalytics.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_fragmentAnalytics)
        }

        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_fragmentSettings)
        }

        binding.btnTraining.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_fragmentTraining)
        }
    }
}