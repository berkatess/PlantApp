package com.example.plantapp.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.example.plantapp.R
import com.example.plantapp.databinding.FragmentHomePageBinding

class HomePageFragment : Fragment() {

    private lateinit var binding : FragmentHomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.textviewWelcome.text = HtmlCompat.fromHtml(
//            getString(R.string.header_text),
//            HtmlCompat.FROM_HTML_MODE_LEGACY
//        )

        binding.bottomText.text = HtmlCompat.fromHtml(
            getString(R.string.bottom_text),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }


}