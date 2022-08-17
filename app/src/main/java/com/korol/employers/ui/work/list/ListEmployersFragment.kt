package com.korol.employers.ui.work.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.korol.employers.R
import com.korol.employers.app.App
import com.korol.employers.databinding.FragmentListEmployersBinding

class ListEmployersFragment : Fragment() {
    private lateinit var binding: FragmentListEmployersBinding

    @javax.inject.Inject
    lateinit var vmFactory: ListEmployersViewModelFactory

    private lateinit var listEmployersViewModel: ListEmployersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListEmployersBinding.inflate(inflater, container, false)

        (activity?.applicationContext as App).appComponent.injectListEmployersFragment(this)

        listEmployersViewModel =
            ViewModelProvider(this, vmFactory).get(ListEmployersViewModel::class.java)

        val text=listEmployersViewModel.getSearchText()
        binding.searchView.setQuery(text, false)
        if (text != "") binding.btnFilters.background.setTint(
            ContextCompat.getColor(
                requireContext(),
                R.color.blue
            )
        ) else {
            binding.btnFilters.background.setTint(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.grey
                )
            )
        }
        binding.searchView.clearFocus()

        listEmployersViewModel.loadHasVacancy()

        listEmployersViewModel.fillAdapter()


        binding.btnFilters.setOnClickListener {
            val action =
                ListEmployersFragmentDirections.actionListEmployersFragmentToFiltersFragment()
            Navigation.findNavController(binding.root).navigate(action)
        }

        listEmployersViewModel.listEmployer.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.rvEmployers.adapter = RvListEmployersAdapter(it)
                if (it.isEmpty()) {
                    binding.tvNoData.visibility = View.VISIBLE
                } else {
                    binding.tvNoData.visibility = View.GONE

                }
            } else {
                Log.d("tag", "listEmployer == null")
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                listEmployersViewModel.saveSearchText(text!!)
                if (text != "") binding.btnFilters.background.setTint(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.blue
                    )
                ) else {
                    binding.btnFilters.background.setTint(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grey
                        )
                    )
                }
                return true
            }
        })

        val searchCloseButtonId: Int = binding.searchView.context.resources
            .getIdentifier("android:id/search_close_btn", null, null)
        val closeButton: ImageView =
            binding.searchView.findViewById(searchCloseButtonId) as ImageView

// Set on click listener
        closeButton.setOnClickListener(View.OnClickListener {
            binding.searchView.setQuery("", false)
        })

        return binding.root
    }

}