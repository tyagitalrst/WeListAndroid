package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.youtube.player.YouTubeStandalonePlayer


import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.viewmodel.WeListViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailLongTermFragment: Fragment() {

        companion object {
            fun newInstance() = DetailLongTermFragment()
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
                weListId = DetailLongTermFragmentArgs.fromBundle(it).id
            }


            viewModel = ViewModelProviders.of(this).get(WeListViewModel::class.java)
            observeViewModel()



            val requestOptions = RequestOptions()
                .placeholder(R.drawable.img_placeholder)
                .override(300, 200)
            Glide.with(this)
                .load(THUMBNAIL)
                .apply(requestOptions)
                .into(thumbnailYoutube)


            playButton.setOnClickListener {
                checkingConnectiontoPlayVideo()
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
                    VIDEO_ID = weList.youtubeId
                    THUMBNAIL = weList.youtubeThumbnail
                }
            })

        }


    fun checkingConnectiontoPlayVideo() {

        val connectiviyManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectiviyManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnected == true

        val wifiManager = activity?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiConnected : Boolean = wifiManager.isWifiEnabled == true

        val youtubeIntent = YouTubeStandalonePlayer.createVideoIntent(activity, API_KEY, VIDEO_ID)
        val builder = AlertDialog.Builder(getActivity())


        if(isConnected && wifiConnected) {

            startActivity(youtubeIntent)

        }else if(isConnected && !wifiConnected) {

            builder.setMessage(R.string.ask_for_wifi)
                .setTitle(R.string.title_ask_for_wifi)
                .setCancelable(true)
                .setPositiveButton(R.string.connect_wifi) { dialog, id ->

                    startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))

                }
                .setNegativeButton(R.string.play) { dialog, id ->

                    startActivity(youtubeIntent)

                }

            val alert = builder.create()
            alert.show()
        } else if(!isConnected){
            builder.setMessage(R.string.ask_for_connection)
                .setTitle(R.string.title_dialog)
                .setCancelable(false)
                .setPositiveButton(R.string.mobile_data) { dialog, id ->

                    startActivity(Intent(Settings.ACTION_DATA_USAGE_SETTINGS))

                }
                .setNegativeButton(R.string.cancel) { dialog, id ->

                    dialog.dismiss()

                }
            val alert = builder.create()
            alert.show()
        }
    }

}

