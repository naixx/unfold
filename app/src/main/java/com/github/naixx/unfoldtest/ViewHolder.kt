package com.github.naixx.unfoldtest

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

sealed class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(cell: Cell)

    companion object {
        fun create(parent: ViewGroup, viewType: Int, callbacks: ViewCallbacks): ViewHolder = when (viewType) {
            R.layout.cell_image_listitem -> ImageViewHolder(parent.inflate(viewType))
            R.layout.cell_video_listitem -> VideoViewHolder(parent.inflate(viewType))
            R.layout.cell_empty_listitem -> EmptyViewHolder(parent.inflate(viewType), callbacks)
            else -> throw IllegalArgumentException("Should not happen ever")
        }
    }
}

private class ImageViewHolder(itemView: View) : ViewHolder(itemView) {
    override fun bind(cell: Cell) {
        Picasso.with(itemView.context).load((cell as ImageCell).path).into(itemView as ImageView)
    }
}

private class EmptyViewHolder(itemView: View, val callbacks: ViewCallbacks) : ViewHolder(itemView) {
    override fun bind(cell: Cell) {
        itemView.setOnClickListener { callbacks.showChooserForCell(cell as EmptyCell) }
    }
}

private class VideoViewHolder(itemView: View) : ViewHolder(itemView) {
    override fun bind(cell: Cell) {
        val videoView = itemView as VideoView
        val videoCell = cell as VideoCell
        videoView.setVideoURI(videoCell.path)
        if (videoCell.play)
            videoView.start()
        videoView.setOnCompletionListener {
            videoCell.play = false
        }
        videoView.setOnClickListener {
            videoView.start()
        }
    }
}


