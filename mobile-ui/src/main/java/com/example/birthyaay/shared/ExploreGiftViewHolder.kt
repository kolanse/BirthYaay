package com.example.birthyaay.shared

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.GradientDrawable
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import coil.bitmap.BitmapPool
import coil.load
import coil.size.Size
import coil.transform.Transformation
import com.example.birthyaay.R
import com.example.birthyaay.databinding.ExploreGiftItemBinding
import com.example.birthyaay.models.ExploreGift
import com.example.birthyaay.util.ColorConverterUtils
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class ExploreGiftViewHolder(
    private val binding: ExploreGiftItemBinding
) :
    RecyclerView.ViewHolder(binding.root) {


    companion object {
        const val TRANSFORMATION_KEY = "transformation_key"
    }

    fun bind(exploreGift: ExploreGift) {
        binding.apply {

            exploreGiftTitleTv.text = exploreGift.giftTitle

//            val drawable: GradientDrawable = root.background as GradientDrawable
//
//            drawable.alpha = 50

            exploreGiftIv.load(exploreGift.giftImage)

//            {
//
//                transformations(object : Transformation{
//                    override fun key() = TRANSFORMATION_KEY
//
//                    override suspend fun transform(
//                        pool: BitmapPool,
//                        input: Bitmap,
//                        size: Size
//                    ): Bitmap {
//
//                        Palette.from(input).generate { palette ->
//                            // Use generated instance
//
//                            binding.apply {
//
//                                drawable.apply {
//                                    setColor(
//                                        palette?.vibrantSwatch?.rgb ?: ContextCompat.getColor(
//                                            binding.root.context,
//                                            R.color.grey_25
//                                        )
//                                    )
//                                    setStroke(
//                                        1, palette?.darkMutedSwatch?.rgb ?: ContextCompat.getColor(
//                                            binding.root.context,
//                                            R.color.grey_25
//                                        )
//                                    )
//                                }
//                            }
//                        }
//
//                        return input
//
//                    }
//
//                })
//
//            }


        }
    }


}