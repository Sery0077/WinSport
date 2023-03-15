package sery.vlasenko.winsport.ui.askTrainer

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import sery.vlasenko.winsport.R
import sery.vlasenko.winsport.databinding.FragmentAskTrainerBinding
import sery.vlasenko.winsport.model.DataResult
import sery.vlasenko.winsport.utils.SnackBarHelper

class FragmentAskTrainer : Fragment() {

    private lateinit var binding: FragmentAskTrainerBinding
    private val viewModel: ViewModelAskTrainer by viewModels()

    private lateinit var sp: SharedPreferences

    override fun onAttach(context: Context) {
        sp = context.getSharedPreferences(getString(R.string.question_prefs), Context.MODE_PRIVATE)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAskTrainerBinding.inflate(inflater)

        observeAskResult()
        observeResponseResult()

        readQuestion()

        return binding.root
    }

    private fun observeAskResult() {
        viewModel.askResult.observe(viewLifecycleOwner) {
            when (it.status) {
                DataResult.Status.SUCCESS -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.ask_success),
                        Toast.LENGTH_SHORT
                    )
                        .show()

                    binding.twQuestion.text = binding.etQuestion.text?.toString()
                    binding.etQuestion.text?.clear()
                    binding.twAnswer.text = getString(R.string.answer_processing)
                }
                DataResult.Status.ERROR -> {
                    SnackBarHelper.errorSnackBar(binding.root) {
                        askAnswer()
                    }
                }
                DataResult.Status.LOADING -> {

                }
            }
        }
    }

    private fun observeResponseResult() {
        viewModel.responseResult.observe(viewLifecycleOwner) {
            when (it.status) {
                DataResult.Status.SUCCESS -> {
                    binding.twAnswer.text = it.data?.response

                }
                DataResult.Status.ERROR -> {
                    SnackBarHelper.errorSnackBar(binding.root) {
                        viewModel.getAnswer()
                    }
                }
                DataResult.Status.LOADING -> {
                    binding.twAnswer.text = getString(R.string.answer_processing)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnAsk.setOnClickListener {
            askAnswer()
        }

        binding.twQuestion.text = sp.getString(getString(R.string.question), "")

        super.onViewCreated(view, savedInstanceState)
    }

    private fun askAnswer() {
        val question = binding.etQuestion.text?.toString()

        require(!question.isNullOrBlank()) { return }

        val questionId = question.hashCode().toString()

        viewModel.ask(question, questionId)
        saveQuestion(question, questionId)
    }

    private fun saveQuestion(question: String, questionId: String) {
        sp.edit()
            .putString(getString(R.string.question_id), questionId)
            .putString(getString(R.string.question), question)
            .apply()
    }

    private fun readQuestion() {
        val questionId = sp.getString(getString(R.string.question_id), "")

        viewModel.getAnswer(questionId)
    }
}