package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import kotlinx.android.synthetic.main.fragment_dashboard.*

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        createList.setOnClickListener{
            Navigation.findNavController(it)
                .navigate(id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.DashboardFragmentDirections.actionDashboardtoCreate())
        }

        shortTerm.setOnClickListener{
            Navigation.findNavController(it)
                .navigate(id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.DashboardFragmentDirections.actionDashtoList())
        }

        longTerm.setOnClickListener{
            Navigation.findNavController(it)
                .navigate(id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.DashboardFragmentDirections.actionDashtoLongList())
        }

    }


}
