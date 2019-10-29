package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.WeList
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.viewmodel.WeListViewModel
import kotlinx.android.synthetic.main.fragment_create_list.*



/**
 * A simple [Fragment] subclass.
 */
class CreateListFragment : Fragment() {

    companion object {
        fun newInstance() = CreateListFragment()
    }

    private lateinit var viewModel: WeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_list, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WeListViewModel::class.java)


        buttonSubmit.setOnClickListener{
            saveNote()
            Navigation.findNavController(it)
                .navigate(CreateListFragmentDirections.actionResultCreate())
        }

    }


    fun saveNote() {
        if (editTextTitle.text.toString().trim().isBlank()
            || editTextNotes.text.toString().trim().isBlank()) {
            Toast.makeText(getActivity(),"Can not insert empty note!", Toast.LENGTH_SHORT).show()
        }

        val newWeList = WeList(editTextTitle.text.toString(),
                            editTextNotes.text.toString(),
                            editTextPrice.text.toString(),
                            editTextLink.text.toString())

        viewModel.insert(newWeList)
        Toast.makeText(getActivity(),"New list", Toast.LENGTH_SHORT).show()

    }


}
