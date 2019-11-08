package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.WeListLongTerm
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.fragment.ListLongTermFragmentDirections
import kotlinx.android.synthetic.main.content_list.view.*

class WeListLongTermAdapter: RecyclerView.Adapter<WeListLongTermAdapter.WeListLongTermViewHolder>() {

    private var weListsLongTerm: List<WeListLongTerm> = ArrayList()

    class WeListLongTermViewHolder(var view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeListLongTermViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.content_list, parent, false)
        return WeListLongTermViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeListLongTermViewHolder, position: Int) {
        val currentWeList = weListsLongTerm[position]
        holder.view.image.setImageURI(Uri.parse(currentWeList.image))
        holder.view.title.text = currentWeList.title
        holder.view.price.text = currentWeList.price
        holder.view.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(ListLongTermFragmentDirections.actionDetailFragmentLong().setId(position))
        }
    }

    override fun getItemCount(): Int {
        return weListsLongTerm.size
    }

    fun setWeListsLongTerm(weListsLongTerm: List<WeListLongTerm>) {
        this.weListsLongTerm = weListsLongTerm
        notifyDataSetChanged()
    }
}