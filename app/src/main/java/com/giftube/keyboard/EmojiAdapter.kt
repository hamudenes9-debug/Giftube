package com.giftube.keyboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmojiAdapter(
    private val context: Context,
    private val emojis: List<String> = listOf("😊", "😂", "🥰", "😍", "🤩", "😘", "😗", "😙", "😚", "😋", "😜", "😝", "🤑", "🤗", "🤔", "🤨", "😐", "😑", "😶", "🙄", "😏", "😣", "😥", "😮", "🤐", "😯", "😴", "😫", "🥱", "😤", "🤯", "🧐", "😱", "😨", "😰", "😢", "😭", "😱", "😲", "😳", "😵", "😡", "😠", "🤬", "😷", "🤒", "🤕", "🤢", "🤮", "🤧", "😇", "🤠", "🤡", "🤥", "🤫", "🤭", "🧐", "🤓", "😈", "👹", "👺", "💀", "👻", "👽", "🤖", "💩", "😺", "😸", "😹", "😻", "😼", "😽", "🙀", "😿", "😾")
) : RecyclerView.Adapter<EmojiAdapter.EmojiViewHolder>() {

    inner class EmojiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.emoji_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_emoji, parent, false)
        return EmojiViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmojiViewHolder, position: Int) {
        holder.textView.text = emojis[position]
        holder.itemView.setOnClickListener {
            // Insert emoji into InputConnection
            // (Service access needed via interface or callback)
        }
    }

    override fun getItemCount(): Int = emojis.size
}
