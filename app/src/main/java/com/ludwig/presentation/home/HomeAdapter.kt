package com.ludwig.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ludwig.R
import com.ludwig.data.entities.SentenceEntity

/**
 * Created by cuongpm on 2019-05-19.
 */

class HomeAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val sentences: MutableList<SentenceEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_sentence, parent, false)
        )
    }

    override fun getItemCount(): Int = sentences.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeViewHolder -> holder.bind(position, sentences[position])
        }
    }

    fun setData(listSentences: List<SentenceEntity>) {
        sentences.clear()
        sentences.addAll(listSentences)
        notifyDataSetChanged()
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvNumber = itemView.findViewById<TextView>(R.id.tv_number)
        private val tvSentence = itemView.findViewById<TextView>(R.id.tv_sentence)
        private val tvAuthor = itemView.findViewById<TextView>(R.id.tv_author)

        fun bind(position: Int, sentence: SentenceEntity) {
            sentence.let {
                tvNumber.text = "$position"
                tvAuthor.text = it.source
                tvSentence.text = it.content
            }
        }
    }
}