package sery.vlasenko.winsport.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import sery.vlasenko.winsport.R
import sery.vlasenko.winsport.databinding.FragmentAuthBinding

class FragmentAuth : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setClickers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setClickers() {
        binding.btnStart.setOnClickListener {
            val name = binding.etName.text?.toString()
            val height = binding.etHeight.text?.toString()?.toIntOrNull()
            val weight = binding.etWeight.text?.toString()?.toIntOrNull()

            require(!name.isNullOrEmpty()) { return@setOnClickListener }
            require(height != null && height > 0) { return@setOnClickListener }
            require(weight != null && weight > 0) { return@setOnClickListener }

            saveData(name, height, weight)
            navigate()
        }
    }

    private fun saveData(name: String, height: Int, weight: Int) {
        requireActivity().getSharedPreferences(getString(R.string.auth_prefs), Context.MODE_PRIVATE)
            .edit()
            .putString(getString(R.string.auth_name), name)
            .putInt(getString(R.string.auth_height), height)
            .putInt(getString(R.string.auth_weight), weight)
            .apply()
    }

    private fun navigate() {
        findNavController().navigate(R.id.action_authFragment_to_menuFragment)
    }
}