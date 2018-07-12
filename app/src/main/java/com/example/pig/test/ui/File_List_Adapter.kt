package com.example.pig.test.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pig.test.R
import com.vincent.filepicker.filter.entity.NormalFile
import kotlinx.android.synthetic.main.file_item.view.*

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val fileName: TextView = view.fileName
    val fileSize: TextView = view.fileSize
    val fileParentFolder: TextView = view.fileMime
}

class FileListAdapter(private val items: ArrayList<NormalFile>?) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.file_item,
                p0, false))
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.fileName.text = items!![p1].name
        p0.fileSize.text = "Size: " + items[p1].size.toString()
        p0.fileParentFolder.text = items[p1].mimeType
    }
}