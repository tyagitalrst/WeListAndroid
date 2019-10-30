package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.WeList
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.viewmodel.WeListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: WeListViewModel
    private val weListAdapter = WeListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WeListViewModel::class.java)

        weLists.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = weListAdapter
        }

        observeViewModel()

        floatingActionButton.setOnClickListener{
            Navigation.findNavController(it)
                .navigate(ListFragmentDirections.actionCreateList())
        }

    }


    fun observeViewModel() {
        viewModel.getAllWeList().observe(this,
            Observer<List<WeList>> { t -> weListAdapter.setWeLists(t!!) })
    }


}
