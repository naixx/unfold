package com.github.naixx.unfoldtest

import android.net.Uri
import androidx.annotation.LayoutRes

sealed class Cell(@LayoutRes val resId: Int)
data class ImageCell(val path: Uri) : Cell(R.layout.cell_image_listitem)
data class VideoCell(val path: Uri) : Cell(R.layout.cell_video_listitem) {
    var play: Boolean = false
}

class EmptyCell : Cell(R.layout.cell_empty_listitem)
