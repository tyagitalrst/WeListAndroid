package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "welist_table")
data class WeList(
    var title: String,
//    var image: Int,
    var notes: String,
    var price: String,
    var link: String

) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}