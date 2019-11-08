package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.youtube.player.YouTubeStandalonePlayer


import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.viewmodel.WeListViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailLongTermFragment: Fragment() {

        companion object {
            fun newInstance() =
                DetailLongTermFragment()
            private var VIDEO_ID = ""
            private var THUMBNAIL = ""
        }

        private lateinit var viewModel: WeListViewModel
        private var weListId = 0
        private var API_KEY = "AIzaSyD331YQUKyZK_sY7LSXxFUO1Q8SoUjB6GM"

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_detail, container, false)
        }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)

            arguments?.let {
                weListId = id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.DetailFragmentArgs.fromBundle(
                    it
                ).id
            }


            viewModel = ViewModelProviders.of(this).get(WeListViewModel::class.java)
            observeViewModel()



            val requestOptions = RequestOptions()
                .placeholder(R.drawable.img_placeholder)
                .override(300, 200)
            Glide.with(this)
                .load(id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.fragment.DetailLongTermFragment.Companion.THUMBNAIL)
                .apply(requestOptions)
                .into(thumbnailYoutube)

            buttonDelete.setOnClickListener {
                viewModel.deleteList(weListId + 1)
                Navigation.findNavController(it)
                    .navigate(id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.DetailFragmentDirections.actionListFragment())
            }


            playButton.setOnClickListener {
                val intent = YouTubeStandalonePlayer.createVideoIntent(activity, API_KEY,
                    id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.fragment.DetailLongTermFragment.Companion.VIDEO_ID
                )
                startActivity(intent)
            }


        }

        fun observeViewModel() {

            viewModel.detailLongTermList(weListId + 1).observe(this, Observer { weList ->
                weList?.let {
                    imageView.setImageURI(Uri.parse(weList.image))
                    titleDetails.text = weList.title
                    notesContentDetails.text = weList.notes
                    priceContentDetails.text = weList.price
                    linkContentDetails.text = weList.link
                    id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.fragment.DetailLongTermFragment.Companion.VIDEO_ID = weList.youtubeId
                    id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.fragment.DetailLongTermFragment.Companion.THUMBNAIL = weList.youtubeThumbnail
                }
            })

        }

}

