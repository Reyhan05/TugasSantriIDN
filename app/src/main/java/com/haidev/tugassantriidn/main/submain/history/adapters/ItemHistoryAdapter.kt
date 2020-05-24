package com.haidev.tugassantriidn.main.submain.history.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haidev.tugassantriidn.R
import com.haidev.tugassantriidn.databinding.ItemHistoryBinding
import com.haidev.tugassantriidn.main.submain.history.models.RecordHistory
import com.haidev.tugassantriidn.main.submain.history.viewmodels.ItemHistoryViewModel

class ItemHistoryAdapter(
    private val context: Context,
    private var listKasus: MutableList<RecordHistory>
) :
    RecyclerView.Adapter<ItemHistoryAdapter.ItemHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemHistoryViewHolder {
        val binding: ItemHistoryBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_history,
                parent,
                false
            )
        return ItemHistoryViewHolder(
            binding, context
        )
    }

    override fun getItemCount(): Int {
        return listKasus.size
    }

    override fun onBindViewHolder(holder: ItemHistoryViewHolder, position: Int) {
        val fixPosition = holder.adapterPosition
        holder.bindBinding(listKasus[fixPosition])
    }

    class ItemHistoryViewHolder(private val binding: ItemHistoryBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var viewModel: ItemHistoryViewModel

        fun bindBinding(model: RecordHistory) {
            viewModel =
                ItemHistoryViewModel(
                    model,
                    binding,
                    context
                )
            binding.viewmodel = viewModel
            binding.executePendingBindings()
        }

    }
}