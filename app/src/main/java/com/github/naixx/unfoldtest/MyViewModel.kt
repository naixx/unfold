package com.github.naixx.unfoldtest

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private val _items: MutableLiveData<List<Cell>> = MutableLiveData()
    val items: LiveData<List<Cell>> = _items

    private fun replace(empty: EmptyCell, cell: Cell) {
        val l = _items.value ?: emptyList()
        _items.postValue(l.map { if (it == empty) cell else it })
    }

    fun addEmpty() {
        _items.postValue((_items.value ?: emptyList()) + EmptyCell())
    }

    fun replaceWithImage(empty: EmptyCell, uri: Uri) {
        replace(empty, ImageCell(uri))
    }

    fun replaceWithVideo(empty: EmptyCell, uri: Uri) {
        replace(empty, VideoCell(uri).apply { play = true })
    }
}
