package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.WeList
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.viewmodel.WeListViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: WeListViewModel
    private var weListId = 0

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
    }

    fun observeViewModel() {

        viewModel.detailList(weListId+1).observe(this, Observer { weList ->
            weList?.let {
//                imageView.setImageResource(film.image)
                titleDetails.text = weList.title
                notesContentDetails.text = weList.notes
                priceContentDetails.text = weList.price
                linkContentDetails.text = weList.link
            }
        })

    }


}




