package sery.vlasenko.winsport.ui.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import sery.vlasenko.winsport.R

class BottomSheet private constructor(): BottomSheetDialogFragment() {

    companion object {
        private const val TEXT_KEY = "text_key"

        fun newInstance(trainingText: String): BottomSheet {
            val bottomSheetFragment = BottomSheet()

            val args = Bundle()
            args.putString(TEXT_KEY, trainingText)

            bottomSheetFragment.arguments = args

            return bottomSheetFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.bottom_card_view, container, false)

        val tw = view.findViewById<TextView>(R.id.tw_training_text)
        tw.text = arguments?.getString(TEXT_KEY, "")

        return view
    }
}