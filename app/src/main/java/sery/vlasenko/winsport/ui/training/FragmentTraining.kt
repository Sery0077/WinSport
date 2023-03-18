package sery.vlasenko.winsport.ui.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import sery.vlasenko.winsport.R
import sery.vlasenko.winsport.databinding.FragmentTrainingBinding
import sery.vlasenko.winsport.model.DataResult
import sery.vlasenko.winsport.utils.DayOfWeekHelper
import sery.vlasenko.winsport.utils.SnackBarHelper
import java.time.LocalDate
import java.util.*

class FragmentTraining: Fragment() {

    private lateinit var binding: FragmentTrainingBinding
    private val viewModelTraining: ViewModelTraining by viewModels()

    private var errorSnackBar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingBinding.inflate(inflater)
        viewModelTraining.trainingResult.observe(viewLifecycleOwner) {
            when (it.status) {
                DataResult.Status.SUCCESS -> {
                    Glide.with(requireContext())
                        .load(it.data?.img)
                        .into(binding.ivImage)

                    binding.twTrainingText.text = it.data?.text
                }
                DataResult.Status.ERROR -> {
                    errorSnackBar = SnackBarHelper.errorSnackBar(binding.root, null) {
                        viewModelTraining.getTraining()
                    }

                    errorSnackBar?.show()
                }
                DataResult.Status.LOADING -> {

                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setClickers()
        binding.twWeekday.text = DayOfWeekHelper.getDayOfWeekRU()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setClickers() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnExpandText.setOnClickListener {
            val dialog = BottomSheet.newInstance(viewModelTraining.trainingResult.value?.data?.text ?: "")

            dialog.show(childFragmentManager, "sfasfsadfas")
        }
    }
}