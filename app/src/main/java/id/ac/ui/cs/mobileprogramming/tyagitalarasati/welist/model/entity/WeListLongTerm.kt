package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "welistlong_table")
data class WeListLongTerm(
    var image: String,
    var title: String,
    var notes: String,
    var price: String,
    var link: String,
    var youtubeId: String,
    var youtubeThumbnail: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}