package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
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
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
    }

    private lateinit var viewModel: WeListViewModel
    private var imageList = ""


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


        buttonPhotos.setOnClickListener{
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (getActivity()?.getApplicationContext()?.
                    let { it1 -> checkSelfPermission(it1, Manifest.permission.WRITE_EXTERNAL_STORAGE) }
                    != PackageManager.PERMISSION_GRANTED){

                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    //permission already granted
                    pickImageFromGallery();
                }
            }
            else{
                pickImageFromGallery();
            }
        }

        buttonSubmit.setOnClickListener{
            if (editTextTitle.text.toString().trim().isBlank()
                || editTextNotes.text.toString().trim().isBlank()) {
                Toast.makeText(activity,"Can not insert empty note!", Toast.LENGTH_SHORT).show()
            } else {
                saveList()
                Navigation.findNavController(it)
                    .navigate(CreateListFragmentDirections.actionResultCreate())

            }

        }

    }


    private fun saveList() {
        val newWeList = WeList(
            editTextTitle.text.toString(),
            editTextNotes.text.toString(),
            editTextPrice.text.toString(),
            editTextLink.text.toString())

        viewModel.insert(newWeList)
        Toast.makeText(activity,"New list", Toast.LENGTH_SHORT).show()
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(activity, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            imageList = (data?.data).toString()
            imageViewCreate.setImageURI(data?.data)
        }
    }


}
