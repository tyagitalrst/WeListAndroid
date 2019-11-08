package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.WeList
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.fragment.ListFragmentDirections
import kotlinx.android.synthetic.main.content_list.view.*


class WeListAdapter: RecyclerView.Adapter<WeListAdapter.WeListViewHolder>(){

    private var weLists: List<WeList> = ArrayList()

    class WeListViewHolder(var view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.content_list, parent, false)
        return WeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeListViewHolder, position: Int) {
        val currentWeList = weLists[position]
        holder.view.image.setImageURI(Uri.parse(currentWeList.image))
        holder.view.title.text = currentWeList.title
        holder.view.price.text = currentWeList.price
        holder.view.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(ListFragmentDirections.actionDetailFragment().setId(position))
        }
    }

    override fun getItemCount(): Int {
        return weLists.size
    }

    fun setWeLists(weLists: List<WeList>) {
        this.weLists = weLists
        notifyDataSetChanged()
    }

}
