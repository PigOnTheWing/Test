package com.example.pig.test.ui

import android.app.Activity
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.example.pig.test.R
import kotlinx.android.synthetic.main.upload_fragment.*
import java.util.ArrayList

import com.vincent.filepicker.activity.NormalFilePickActivity
import com.vincent.filepicker.activity.BaseActivity.IS_NEED_FOLDER_LIST
import com.vincent.filepicker.Constant
import com.vincent.filepicker.filter.entity.NormalFile
import kotlinx.android.synthetic.main.upload_fragment.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import web.*

// import droidninja.filepicker.FilePickerBuilder

class UploadFiles : Fragment() {

    private var transferNum: Int = 1
    private var chosenFiles: ArrayList<NormalFile>? = null
    private val extensions: Map<String, String> = mapOf(
            "image/jpeg" to ".jpg",
            "image/png" to ".png",
            "application/pdf" to ".pdf"
    )
    private var jwtToken: String = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.upload_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        browseBtn.setOnClickListener {
            val intent = Intent(activity, NormalFilePickActivity::class.java)
            intent.putExtra(IS_NEED_FOLDER_LIST, true)
            intent.putExtra(Constant.MAX_NUMBER, 5)
            intent.putExtra(NormalFilePickActivity.SUFFIX,
                    arrayOf(".jpg", ".png", ".pdf"))
            startActivityForResult(intent, Constant.REQUEST_CODE_PICK_FILE)
        }

        //Couldn't debug in time

        /**uploadBtn.setOnClickListener {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://dev.wetransfer.com/v1/")
                    .build()

            val weTransferApi = retrofit.create(FileTransferService::class.java)
            weTransferApi.authenticate().enqueue(object : Callback<AuthResponse> {
                override fun onFailure(call: Call<AuthResponse>?, t: Throwable?) {
                }

                override fun onResponse(call: Call<AuthResponse>?, response: Response<AuthResponse>?) {
                    if (response!!.body()!!.success)
                        jwtToken = response.body()!!.token
                        weTransferApi.createTransfer(jwtToken,
                                CreateTransferBody("Transfer$transferNum")).enqueue(object : Callback<OnCreateResponse> {
                            override fun onResponse(call: Call<OnCreateResponse>?, response: Response<OnCreateResponse>?) {
                                val map = mapOf("items" to getListOfFileObj())
                                weTransferApi.addItems(jwtToken, response!!.body()!!.id, map).enqueue(object : Callback<List<FileResponse>> {
                                    override fun onResponse(call: Call<List<FileResponse>>?, response: Response<List<FileResponse>>?) {
                                        for (file in response!!.body()!!.iterator()){
                                            //Get upload URLs
                                        }
                                    }

                                    override fun onFailure(call: Call<List<FileResponse>>?, t: Throwable?) {
                                    }
                                })
                            }

                            override fun onFailure(call: Call<OnCreateResponse>?, t: Throwable?) {
                            }
                        })
                        transferNum++
                }
            })
        }*/
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            chosenFiles = data?.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE)

            fileView.layoutManager = LinearLayoutManager(activity)
            fileView.adapter = FileListAdapter(chosenFiles)
            constraintLayout.section_label.visibility = View.INVISIBLE
            constraintLayout.uploadBtn.isEnabled = true
        }

    }

    private fun getListOfFileObj(): List<FileObj>{
        val l : MutableList<FileObj> = mutableListOf()
        for (file in chosenFiles!!.iterator()) {
            l.add(FileObj(file.name, "file",
                    "${file.name}${extensions[file.mimeType]}", file.size))
        }
        return l
    }
}