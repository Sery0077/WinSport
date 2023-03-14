package sery.vlasenko.winsport.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import sery.vlasenko.winsport.R
import sery.vlasenko.winsport.databinding.FragmentSettingsBinding

class FragmentSettings: Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setClickers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setClickers() {
        binding.twClearData.setOnClickListener {
            requireActivity().getSharedPreferences(getString(R.string.auth_prefs), Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply()

            findNavController().navigate(R.id.action_fragmentSettings_to_authFragment)
        }
    }
}