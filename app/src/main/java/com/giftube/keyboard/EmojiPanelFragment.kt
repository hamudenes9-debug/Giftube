package com.giftube.keyboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EmojiPanelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.panel_emoji, container, false)
        
        val recycler = view.findViewById<RecyclerView>(R.id.emoji_recycler)
        recycler.layoutManager = GridLayoutManager(requireContext(), 8)
        
        // EmojiAdapter(requireContext(), emojiList).also { recycler.adapter = it }
        
        return view
    }
}
