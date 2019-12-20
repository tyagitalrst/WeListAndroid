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

import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.Quotes
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.WeList
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.viewmodel.QuotesViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(QuotesViewModel::class.java)

        observeViewModel()

        createList.setOnClickListener{
            Navigation.findNavController(it)
                .navigate(DashboardFragmentDirections.actionDashboardtoCreate())
        }

        shortTerm.setOnClickListener{
            Navigation.findNavController(it)
                .navigate(DashboardFragmentDirections.actionDashtoList())
        }

        longTerm.setOnClickListener{
            Navigation.findNavController(it)
                .navigate(DashboardFragmentDirections.actionDashtoLongList())
        }

    }

    fun observeViewModel() {
        val quotesId = randomNumber()
        
        viewModel.getQuotes(quotesId).observe(this, Observer { quotes ->
            quotes?.let {
                quotesImage.setImageResource(quotes.image)
                quotesText.text = quotes.quotes
            }
        })

    }

    external fun randomNumber(): Int


    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }


}
