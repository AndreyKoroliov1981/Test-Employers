package com.korol.employers.ui.work.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.korol.employers.R
import com.korol.employers.databinding.RvCompanyItemBinding
import com.korol.employers.models.Employer

class RvListEmployersAdapter(private val employerList: List<Employer>) :
    RecyclerView.Adapter<RvListEmployersAdapter.ListEmployersViewHolder>() {
    inner class ListEmployersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var viewBinding: RvCompanyItemBinding
        fun bind(listItem: Employer) {
            viewBinding = RvCompanyItemBinding.bind(itemView)
            if (listItem.logo_urls?.original!=null) {
                Glide.with(itemView.context)
                    .load(listItem.logo_urls.original)
                    .placeholder(R.drawable.ic_downloading)
                    .error(R.drawable.ic_error)
                    .into(viewBinding.ivLogotype)
            } else {
                viewBinding.ivLogotype.setImageDrawable(null)
            }

            viewBinding.tvNameCompany.text = listItem.name
            val text=itemView.context.getString(R.string.textOpenVacancy, listItem.open_vacancies);
            viewBinding.tvVacancy.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListEmployersViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_company_item, parent, false)
        return ListEmployersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListEmployersViewHolder, position: Int) {
        val listItem = employerList[position]
        holder.bind(listItem)
    }

    override fun getItemCount() = employerList.size
}

