package com.example.myapplication.dataBase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.AccountListItemViewBinding

class UserAccountAdapter : ListAdapter<UserAccount,UserAccountAdapter.ItemHolder>(QuestionDiffCallback) {

    class ItemHolder(val binding: AccountListItemViewBinding):RecyclerView.ViewHolder(binding.root)

    object QuestionDiffCallback : DiffUtil.ItemCallback<UserAccount>() {

        override fun areItemsTheSame(oldItem: UserAccount, newItem: UserAccount): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserAccount, newItem: UserAccount): Boolean {
            return oldItem.ID == newItem.ID

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding: AccountListItemViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.account_list_item_view,
            parent,
            false
        )
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding.account = getItem(position)
    }
}