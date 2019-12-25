package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import android.app.ActivityManager
import android.content.Context

import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.viewmodel.QuotesViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.util.MusicService

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
        buttonStopMusic.visibility = View.INVISIBLE

        observeViewModel()

        createList.setOnClickListener{
            Navigation.findNavController(it)
                .navigate(DashboardFragmentDirections.actionDashboardtoCreate())
        }

        createReminder.setOnClickListener{
            Navigation.findNavController(it)
                .navigate(DashboardFragmentDirections.dashboardtoReminder())
        }

        shortTerm.setOnClickListener{
            Navigation.findNavController(it)
                .navigate(DashboardFragmentDirections.actionDashtoList())
        }

        longTerm.setOnClickListener{
            Navigation.findNavController(it)
                .navigate(DashboardFragmentDirections.actionDashtoLongList())
        }

        buttonPlayMusic.setOnClickListener {
            activity?.startService(Intent(activity, MusicService::class.java))
            buttonStopMusic.visibility = View.VISIBLE
            buttonPlayMusic.visibility = View.INVISIBLE

        }

        buttonStopMusic.setOnClickListener {
            activity?.stopService(Intent(activity, MusicService::class.java))
            buttonPlayMusic.visibility = View.VISIBLE
            buttonStopMusic.visibility = View.INVISIBLE
        }

        if(checkRunningService(MusicService::class.java)) {
            buttonStopMusic.visibility = View.VISIBLE
            buttonPlayMusic.visibility = View.INVISIBLE
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

    private fun checkRunningService(serviceClass: Class<*>): Boolean {
        val activityManager = activity?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    external fun randomNumber(): Int


    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }


}
