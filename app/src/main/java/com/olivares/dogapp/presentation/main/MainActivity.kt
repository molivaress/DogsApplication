package com.olivares.dogapp.presentation.main

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.olivares.dogapp.databinding.ActivityMainBinding
import com.olivares.dogapp.presentation.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by viewModels(factoryProducer = { viewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()
    }

    private fun observeViewModel() {
        mainViewModel.mainStateView.observe(this, {
            if (it.error != null) {
                showError(it.error)
            } else {
                showData(it.dogs.orEmpty())
            }
        })
    }

    private fun showData(list: List<String>) {
        val itemsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        binding.lvDogs.adapter = itemsAdapter
        binding.lvDogs.setOnItemClickListener { _, _, position, _ ->
            val name = itemsAdapter.getItem(position)
            startActivity(DetailActivity.getIntent(this, name.orEmpty()))
        }
    }

    private fun showError(error: Int) {
        Toast.makeText(this, getString(error), Toast.LENGTH_SHORT).show()
    }
}