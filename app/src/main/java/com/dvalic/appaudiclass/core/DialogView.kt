package com.dvalic.appaudiclass.core

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dvalic.appaudiclass.databinding.DialogViewBinding


class DialogView : DialogFragment() {

    private lateinit var binding: DialogViewBinding
    private var title: String? = ""
    private var description: String? = ""
    private var positivebutton: String? = ""
    private var negativebutton: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            title = bundle.getString("title")
            description = bundle.getString("description")
            positivebutton = bundle.getString("positivebutton")
            negativebutton = bundle.getString("negativebutton")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val widthPixels = (resources.displayMetrics.widthPixels * 0.9).toInt()
        dialog?.window?.setLayout(
            widthPixels,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        binding.tvTitle.text = title
        binding.tvSubtitle.text = description
        binding.btnPositiveButton.text = positivebutton
        if (negativebutton != "") {
            binding.btnNegativeButton.visibility = View.VISIBLE
            binding.btnNegativeButton.text = negativebutton
        }
    }
}