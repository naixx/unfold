package com.github.naixx.unfoldtest

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.robertlevonyan.components.picker.ItemModel
import com.robertlevonyan.components.picker.PickerDialog
import kotlinx.android.synthetic.main.cell_list_activity.*


interface ViewCallbacks {
    fun showChooserForCell(cell: EmptyCell)
}

class MainActivity : AppCompatActivity(), ViewCallbacks {

    private var pickerDialog: PickerDialog? = null
    private lateinit var adapter: CellAdapter
    private lateinit var model: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cell_list_activity)
        adapter = CellAdapter(this)
        model = ViewModelProviders.of(this).get(MyViewModel::class.java)
        recyclerView.adapter = adapter

        model.items.observe(this, Observer { adapter.submitList(it) })

        floatingActionButton.setOnClickListener {
            model.addEmpty()
        }
    }

    override fun showChooserForCell(cell: EmptyCell) {
        val items = arrayListOf(ItemModel(ItemModel.ITEM_VIDEO_GALLERY), ItemModel(ItemModel.ITEM_GALLERY), ItemModel(ItemModel.ITEM_VIDEO))
        pickerDialog = PickerDialog.Builder(this).setItems(items).create().apply {
            setPickerCloseListener { i, uri -> onImageSelected(cell, i, uri) }
            show(supportFragmentManager, "")
        }
    }

    private fun onImageSelected(cell: EmptyCell, type: Int, uri: Uri) {
        when (type) {
            ItemModel.ITEM_GALLERY -> model.replaceWithImage(cell, uri)
            ItemModel.ITEM_VIDEO_GALLERY, ItemModel.ITEM_VIDEO -> model.replaceWithVideo(cell, uri)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        pickerDialog?.onPermissionsResult(requestCode, permissions, grantResults)
    }
}
