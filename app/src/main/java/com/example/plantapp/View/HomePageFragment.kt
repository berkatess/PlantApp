package com.example.plantapp.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantapp.Adapter.PlantAdapter
import com.example.plantapp.ViewModel.HomePageViewModel
import com.example.plantapp.databinding.FragmentHomePageBinding
import com.example.plantapp.databinding.FragmentOnboardingOneBinding

class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewModel: HomePageViewModel

    private lateinit var questionsAdapter: PlantAdapter
    private lateinit var categoriesAdapter: PlantAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[HomePageViewModel::class.java]

        setupRecyclerViews()
        observeLiveData()

        viewModel.getCategories()
        viewModel.getQuestions()
    }

    private fun setupRecyclerViews() {
        questionsAdapter = PlantAdapter(emptyList())
        categoriesAdapter = PlantAdapter(emptyList())

        binding.recyclerQuestions.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = questionsAdapter
        }

        binding.recyclerPlantCards.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = categoriesAdapter
        }

    }

    private fun observeLiveData() {
        viewModel.plantModelStatusList.observe(viewLifecycleOwner) { questions ->
            Log.i("TAG", "Questions: $questions")
            questionsAdapter.submitList(questions)
        }

        viewModel.plantModelCategoryList.observe(viewLifecycleOwner) { categories ->
            Log.i("TAG", "Categories: $categories")
            categoriesAdapter.submitList(categories)
        }
    }
}
