package com.example.cryptocurrency

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView

class LoadingViewHolder(view:View) : RecyclerView.ViewHolder(view) {
    val progessBar=view.findViewById<ProgressBar>(R.id.progresBar)
}
