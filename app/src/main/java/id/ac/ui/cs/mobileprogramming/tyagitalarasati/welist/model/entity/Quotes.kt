package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "quotes_table")
data class Quotes (
    var image: Int,
    var quotes: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}