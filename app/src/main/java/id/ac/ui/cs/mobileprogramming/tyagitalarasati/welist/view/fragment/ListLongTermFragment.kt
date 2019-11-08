package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.adapter.WeListLongTermAdapter
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.WeListLongTerm
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.viewmodel.WeListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class ListLongTermFragment : Fragment() {

    companion object {
        fun newInstance() = ListLongTermFragment()
    }


    private lateinit var viewModel: WeListViewModel
    private val weListLongTermAdapter = WeListLongTermAdapter()

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
            adapter = weListLongTermAdapter
        }

        observeViewModel()

        buttonDelete.setOnClickListener {
            viewModel.deleteAllLongTerm()
        }

        floatingActionButton.setOnClickListener{
            Navigation.findNavController(it)
                .navigate(ListLongTermFragmentDirections.actionCreateListLong())
        }

    }

    fun observeViewModel() {
        viewModel.getAllWeListLongTerm().observe(this,
            Observer<List<WeListLongTerm>> { t ->
                weListLongTermAdapter.setWeListsLongTerm(t!!)
                setEmptyState(weListLongTermAdapter.itemCount)
            })

    }

    private fun setEmptyState(length: Int) {

        if (length > 0) {
            weLists.visibility = View.VISIBLE
            emptyState.visibility = View.GONE
            buttonDelete.visibility = View.VISIBLE
        } else {
            weLists.visibility = View.GONE
            emptyState.visibility = View.VISIBLE
            buttonDelete.visibility = View.GONE
        }

    }





}
