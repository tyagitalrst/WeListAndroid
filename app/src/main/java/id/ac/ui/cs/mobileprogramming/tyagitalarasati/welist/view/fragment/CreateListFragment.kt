package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.fragment


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
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.WeList
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.WeListLongTerm
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.viewmodel.WeListViewModel
import kotlinx.android.synthetic.main.fragment_create_list.*


/**
 * A simple [Fragment] subclass.
 */
class CreateListFragment : Fragment() {

    companion object {
        fun newInstance() =
            CreateListFragment()
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }

    private lateinit var viewModel: WeListViewModel
    private var IMAGE_CREATE_LIST = "android.resource://id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist/drawable/img_placeholder"
    private var youtubeID = ""
    private var youtubeThumbnail = ""
    private var typeWishList = ""
    private var price = "Rp "



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
                    requestPermissions(permissions,PERMISSION_CODE);
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


        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (R.id.longButton == checkedId) typeWishList = "long" else typeWishList = "short"

        }


        buttonSubmit.setOnClickListener{
           if (requiredTitle() && requiredNotes() && requiredPrice() && requiredLinkYoutube()) {
               if (typeWishList.contentEquals("long")) saveListLong() else saveListShort()
                Navigation.findNavController(it)
                    .navigate(CreateListFragmentDirections.actionCreatetoDashboard())
            }
        }

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
                    Toast.makeText(activity, R.string.permission, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            IMAGE_CREATE_LIST = (data?.data).toString()
            imageViewCreate.setImageURI(data?.data)
        }
    }



    private fun saveListShort() {
        price += editTextPrice.text.toString()
        val newWeList = WeList(
            IMAGE_CREATE_LIST,
            editTextTitle.text.toString(),
            editTextNotes.text.toString(),
            price,
            editTextLink.text.toString(),
            youtubeID,
            youtubeThumbnail
        )

        viewModel.insert(newWeList)
        Toast.makeText(activity,R.string.new_wishlist, Toast.LENGTH_SHORT).show()
    }

    private fun saveListLong() {
        price += editTextPrice.text.toString()
        val newWeList = WeListLongTerm(
            IMAGE_CREATE_LIST,
            editTextTitle.text.toString(),
            editTextNotes.text.toString(),
            price,
            editTextLink.text.toString(),
            youtubeID,
            youtubeThumbnail
        )

        viewModel.insertLongTerm(newWeList)
        Toast.makeText(activity,R.string.new_wishlist, Toast.LENGTH_SHORT).show()
    }


    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }


    private fun requiredLinkYoutube(): Boolean {
        val userInput = editTextLink.text.toString()
        val link1 = "youtu.be/"
        val link2 = "youtube.com/watch?v="

        if (userInput.trim().isBlank()) {
            editTextLink.setError("This field is required")
            editTextLink.requestFocus()
            return false
        } else {

            if (userInput.contains(link2)) {
                youtubeID = userInput.substringAfter(delimiter = "=")
                youtubeThumbnail = "https://img.youtube.com/vi/$youtubeID/hqdefault.jpg"
                return true
            } else if (userInput.contains(link1)) {
                youtubeID = userInput.substringAfterLast(delimiter = "/")
                youtubeThumbnail = "https://img.youtube.com/vi/$youtubeID/hqdefault.jpg"
                return true
            } else {
                editTextLink.setError("Please only put valid youtube video's link")
                return false
            }
        }
    }

    private fun requiredTitle(): Boolean {
        val userInput = editTextTitle.text.toString()

        if (userInput.trim().isBlank()) {
            editTextTitle.setError("This field is required")
            editTextTitle.requestFocus()
            return false
        }

        return true
    }

    private fun requiredNotes(): Boolean {
        val userInput = editTextNotes.text.toString()

        if (userInput.trim().isBlank()) {
            editTextNotes.setError("This field is required")
            editTextNotes.requestFocus()
            return false
        }

        return true
    }

    private fun requiredPrice(): Boolean {
        val userInput = editTextPrice.text.toString()

        if (userInput.trim().isBlank()) {
            editTextPrice.setError("This field is required")
            editTextPrice.requestFocus()
            return false
        }

        return true
    }


}
