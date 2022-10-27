package com.example.infoaboutcountries

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.infoaboutcountries.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val countryName = binding.countryNameEditText.text.toString()

            lifecycleScope.launch {
                try {
                    val countries = restCountriesService.getCountryByName(countryName)
                    val country = countries[0]

                    binding.flagImageView.load(country.flag)
                    binding.countryNameTextView.text = country.name
                    binding.capitalTextView.text = country.capital
                    binding.areaCountryTextView.text = "${formatNumber(country.area)} км²"
                    binding.populationTextView.text = "${formatNumber(country.population)} чел."
                    binding.languagesTextView.text = country.languages.joinToString { it.name }

                    binding.resultLayout.visibility = View.VISIBLE
                    binding.statusLayout.visibility = View.INVISIBLE
                } catch (e: Exception) {
                    binding.resultLayout.visibility = View.INVISIBLE
                    binding.statusLayout.visibility = View.VISIBLE
                    binding.statusTextView.text = "Страна не найдена"
                    binding.startImageView.visibility = View.INVISIBLE
                    binding.errorImageView.visibility = View.VISIBLE
                }

            }

        }
    }
}