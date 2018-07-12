package web

import retrofit2.http.*
import com.example.pig.test.R
import retrofit2.Call

interface FileTransferService {
    @Headers(
            "Content-Type: application/json",
            "x-api-key: ${R.string.we_transfer_api}"
    )
    @POST("authorize")
    fun authenticate(): Call<AuthResponse>

    @Headers(
            "Content-Type: application/json",
            "x-api-key: ${R.string.we_transfer_api}"
    )
    @POST("transfers")
    fun createTransfer(@Header("Authorization") jwtToken: String,
                       @Body createTransferBody: CreateTransferBody): Call<OnCreateResponse>

    @Headers(
            "Content-Type: application/json",
            "x-api-key: ${R.string.we_transfer_api}"
    )
    @POST("transfers/{transfer_id}/items")
    fun addItems(@Header("Authorization") jwtToken: String,
                 @Path("transfer_id") transferId: String,
                 @Body files: Map<String, List<FileObj>>): Call<List<FileResponse>>

    @Headers(
            "Content-Type: application/json",
            "x-api-key: ${R.string.we_transfer_api}"
    )
    @GET("files/{file_id}/uploads/{part_number}/{multipart_upload_id}")
    fun getUploadUrl(@Header("Authorization") jwtToken: String,
                     @Path("file_id") fileId: String,
                     @Path("part_number") partNumber: Int,
                     @Path("multipart_upload_id") multipartUploadID: String): Call<PartUrl>

    @Headers(
            "Content-Type: application/json",
            "x-api-key: ${R.string.we_transfer_api}"
    )
    @POST("files/{file_id}/uploads/complete")
    fun markComplete(@Header("Authorization") jwtToken: String,
                     @Path("file_id") fileId: String)
}

