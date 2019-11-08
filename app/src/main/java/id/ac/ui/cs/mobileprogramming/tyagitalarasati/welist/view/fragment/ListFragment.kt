package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.WeList
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.viewmodel.WeListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.adapter.*

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

        buttonDelete.setOnClickListener {
            viewModel.deleteAllList()
        }

        floatingActionButton.setOnClickListener{
            Navigation.findNavController(it)
                .navigate(ListFragmentDirections.actionCreateList())
        }


    }


    fun observeViewModel() {
        viewModel.getAllWeList().observe(this,
            Observer<List<WeList>> { t ->
                weListAdapter.setWeLists(t!!)
                setEmptyState(weListAdapter.itemCount)
            })
    }


    private fun setEmptyState(length: Int) {

        if (length > 0) {
            weLists.visibility = View.VISIBLE
            buttonDelete.visibility = View.VISIBLE
            emptyState.visibility = View.GONE
        } else {
            weLists.visibility = View.GONE
            emptyState.visibility = View.VISIBLE
            buttonDelete.visibility = View.GONE
        }
    }

}
