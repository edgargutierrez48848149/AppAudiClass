package com.dvalic.appaudiclass.core

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.dvalic.appaudiclass.databinding.DialogImageBinding

class ImageDialog(
    private val url: String? = null,
    private val drawable: Drawable? = null,
    private val bigPicture: Boolean? = false
): DialogFragment() {

    private lateinit var binding:DialogImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val widthPixels = (resources.displayMetrics.widthPixels * 1)
        dialog?.window?.setLayout(widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT)
        if (bigPicture!=null) {
            if (bigPicture){
                binding.photoView.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            }
        }

        if (url!=null){
            Glide.with(requireContext()).load(url).into(binding.photoView)
        }
        if (drawable!=null){
            binding.photoView.setImageDrawable(drawable)
        }
    }
}