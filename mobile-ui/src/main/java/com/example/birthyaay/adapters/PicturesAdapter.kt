package com.example.birthyaay.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.birthyaay.R
import com.example.birthyaay.databinding.PictureItemBinding
import com.example.birthyaay.shared.PicturesViewHolder
import com.example.birthyaay.util.DataResourceGenerator

class PicturesAdapter(private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<PicturesViewHolder>() {

    private val pictures =
        DataResourceGenerator.pictureStorage.filter { it != "null" } as MutableList

    private var binding: PictureItemBinding? = null
    private val context get() = binding?.root?.context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesViewHolder {
        binding = PictureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PicturesViewHolder(binding!!,
            onItemClick = { picture ->
                onItemClick(picture)
            }
        )
    }

    override fun onBindViewHolder(holder: PicturesViewHolder, position: Int) {
        pictures.forEach {
            Log.d("PIC", it)
            println("PIC $it")
        }
        Log.d("PIC", pictures.size.toString())
        holder.bind(pictures[position])
    }

    override fun getItemCount(): Int = pictures.size

    fun addPicture(pictureUrl: String) {

        if (!pictures.contains(pictureUrl) && pictureUrl.trim().isNotEmpty()) {
            this.pictures.add(pictureUrl)
            notifyItemInserted(pictures.lastIndex)
        } else {
            Toast.makeText(
                context, context?.getString(R.string.image_exists_error),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}