package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.youtube.player.YouTubeStandalonePlayer


import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.WeList
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.viewmodel.WeListViewModel
import kotlinx.android.synthetic.main.content_list.*
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
        private var VIDEO_ID = ""
        private var THUMBNAIL = ""
    }

    private lateinit var viewModel: WeListViewModel
    private var weListId = 0
    private var API_KEY = ""

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
            weListId = DetailFragmentArgs.fromBundle(it).id
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

        buttonDelete.setOnClickListener {
            viewModel.deleteList(weListId + 1)
            Navigation.findNavController(it)
                .navigate(DetailFragmentDirections.actionListFragment())
        }

        button.setOnClickListener {
            val intent = YouTubeStandalonePlayer.createVideoIntent(activity, API_KEY, VIDEO_ID)
            startActivity(intent)
        }


    }

    fun observeViewModel() {

        viewModel.detailList(weListId + 1).observe(this, Observer { weList ->
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

}




