package com.enty.ilinkcatsandducks.ui.cats_ducks

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.ContentValues.TAG
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.enty.ilinkcatsandducks.R
import com.enty.ilinkcatsandducks.common.Resource
import com.enty.ilinkcatsandducks.databinding.FragmentCatOrDuckBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatOrDuckFragment : Fragment() {

    private lateinit var binding: FragmentCatOrDuckBinding
    private var btnPressed = false
    private val viewModel: CatOrDuckViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatOrDuckBinding.inflate(inflater, container, false)

        viewModel.imageUrl.observe(viewLifecycleOwner, Observer {
            Log.e(TAG, "onCreateView: ${it.data}")
            when (it) {
                is Resource.Success -> {
                    loadImg(it.data!!, binding.ivAnimal)
                }
                is Resource.Error -> {
                    Toast.makeText(
                        requireContext(),
                        it.message ?: "An unexpected error occurred",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        })
        val btnListener = object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (view == null) return
                if (!btnPressed) {
                    startAnimation()
                }
                when (view.id) {
                    R.id.btDuck -> {
                        viewModel.loadDuck()
                    }
                    R.id.btCat -> {
                        viewModel.loadCat()
                    }
                }


            }
        }

        binding.btCat.setOnClickListener(btnListener)
        binding.btDuck.setOnClickListener(btnListener)



        return binding.root
    }

    private fun startAnimation() {
        val img = binding.flContainer
        val anim = ValueAnimator.ofInt(img.height, 500)
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            val layoutParams: ViewGroup.LayoutParams = img.getLayoutParams()
            layoutParams.height = `val`
            img.setLayoutParams(layoutParams)
        }
        anim.duration = 200
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
//                img.visibility = View.INVISIBLE
            }

            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                binding.progressBar.visibility = View.VISIBLE
            }
        })
        anim.start()
    }

    private fun loadImg(url: String, view: ImageView){
        Glide
            .with(requireContext())
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    e?.cause?.printStackTrace()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.e(TAG, "onLoadSccess: ")
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.ivAnimal.setImageDrawable(resource)

                    return false
                }
            })
            .centerCrop()
            .into(view)
    }


}