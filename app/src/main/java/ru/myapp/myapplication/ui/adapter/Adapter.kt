package ru.myapp.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.myapp.myapplication.databinding.ItemLayoutBinding

class Adapter (private val onClick: (String) -> Unit) : RecyclerView.Adapter<ItemLayoutVH>() {

    private lateinit var bind: ItemLayoutBinding

    val data = listOf<String>(
        "Element 1",
        "Element 2",
        "Element 3",
        "Element 4"
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemLayoutVH {
        bind = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemLayoutVH(bind)
    }

    override fun onBindViewHolder(
        holder: ItemLayoutVH,
        position: Int
    ) {
        val item = data[position]
        holder.binding.text.text = item
        holder.binding.text.setOnClickListener{(onClick(position.toString()))}
    }

    override fun getItemCount(): Int = data.size

}

class ItemLayoutVH(val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root)