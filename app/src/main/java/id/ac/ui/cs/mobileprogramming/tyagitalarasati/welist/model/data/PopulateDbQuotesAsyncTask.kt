package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.data

import android.os.AsyncTask
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.database.QuotesDatabase
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.Quotes


class PopulateDbQuotesAsyncTask(db: QuotesDatabase?) : AsyncTask<Unit, Unit, Unit>() {
    private val quotesDao = db?.quotesDao()

    override fun doInBackground(vararg p0: Unit?) {
        quotesDao?.insertQuotes(
            Quotes(
                R.drawable.friendship,
                "People say that money is not the key to happiness, but I always figured " +
                        "if you have enough money, you can have a key made. — Joan Rivers"))
        quotesDao?.insertQuotes(
            Quotes(
                R.drawable.spending,
                "Never spend your money before you have earned it. – Thomas Jefferson"))
        quotesDao?.insertQuotes(
            Quotes(
                R.drawable.hand,
                "Making money isn't hard in itself… What's hard is to earn it doing something " +
                        "worth devoting one's life to. ― Carlos Ruiz Zafón"))
        quotesDao?.insertQuotes(
                Quotes(
                    R.drawable.crown,
            "Money can’t buy friends, but you can get a better class of enemy.— Spike Milligan"))
    }
}