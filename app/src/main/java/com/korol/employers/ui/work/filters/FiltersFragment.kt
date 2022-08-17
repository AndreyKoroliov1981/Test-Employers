package com.korol.employers.ui.work.filters

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.korol.employers.app.App
import com.korol.employers.databinding.FragmentFiltersBinding


class FiltersFragment : Fragment() {
    private lateinit var binding: FragmentFiltersBinding

    @javax.inject.Inject
    lateinit var vmFactory: FiltersViewModelFactory

    private lateinit var filtersViewModel: FiltersViewModel

    private var listCheckedEmployers = mutableSetOf<String>()

    private var massivCB= mutableListOf<CheckBox>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFiltersBinding.inflate(inflater, container, false)

        (activity?.applicationContext as App).appComponent.injectFiltersFragment(this)

        filtersViewModel =
            ViewModelProvider(this, vmFactory).get(FiltersViewModel::class.java)

        binding.searchView.setQuery(filtersViewModel.getSearchText(), false)
        binding.searchView.clearFocus()

        createCheckBoxesTypeEmployers()

        if (filtersViewModel.getHasVacancy()) {
            binding.radioButtonHasVacancy.isChecked = true
            binding.radioButtonAllCompany.isChecked = false
        } else {
            binding.radioButtonHasVacancy.isChecked = false
            binding.radioButtonAllCompany.isChecked = true
        }

        binding.radioGroupChoiceVacancy.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener
        { group, checkedId ->
            when (checkedId) {
                com.korol.employers.R.id.radioButtonHasVacancy -> {
                    filtersViewModel.saveHasVacancy(true)
                }
                com.korol.employers.R.id.radioButtonAllCompany -> {
                    filtersViewModel.saveHasVacancy(false)
                }
            }
        })

        binding.btnShow.setOnClickListener {
            filtersViewModel.saveSearchEmployersType(listCheckedEmployers.toList())
            val action = FiltersFragmentDirections.actionFiltersFragmentToListEmployersFragment()
            Navigation.findNavController(binding.root).navigate(action)
        }

        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(text: String?): Boolean {
                    filtersViewModel.saveSearchText(text!!)
                    return true
                }
            })

        binding.btnReset.setOnClickListener {
            filtersViewModel.reset()
        }

        filtersViewModel.updateUI.observe(viewLifecycleOwner){
            if (it) {
                binding.searchView.setQuery("",false)
                binding.radioButtonHasVacancy.isChecked=true

                for (i in 0 until massivCB.size) {
                    massivCB[i].isChecked=false
                }

            }
        }

        return binding.root
    }

    private fun createCheckBoxesTypeEmployers() {
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT, 0F
        )

        val listEmployerType = filtersViewModel.loadEmployersType
        listCheckedEmployers = filtersViewModel.searchEmployersType.toMutableSet()

        val countTypeEmployers = listEmployerType.size

        for (i in 0 until countTypeEmployers) {
            val cbEmployer = CheckBox(context)
            val type = object : TypeToken<HashMap<String, String>>() {}.type
            val testHashMap2: HashMap<String, String> =
                Gson().fromJson(listEmployerType[i], type)
            val getType = testHashMap2.get("name")
            cbEmployer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
            cbEmployer.text = getType
            cbEmployer.isChecked = listCheckedEmployers.contains(listEmployerType[i])
//            cbEmployer.id = i
            massivCB.add(cbEmployer)
            cbEmployer.setOnClickListener {
                for (j in 0 until countTypeEmployers) {
                    if (cbEmployer.isChecked) listCheckedEmployers.add(listEmployerType[i])
                    else listCheckedEmployers.remove(listEmployerType[i])
                }
            }

            binding.llTypeEmployers.addView(cbEmployer, layoutParams)
        }

    }

}