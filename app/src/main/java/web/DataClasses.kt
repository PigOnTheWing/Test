package web

data class AuthResponse(val success: Boolean, val token: String)

data class CreateTransferBody(val name: String)

data class OnCreateResponse (
        val id: String,
        val versionIdentifier: String?,
        val state: String,
        val shortenedUrl: String,
        val name: String,
        val description: String?,
        val size: Int,
        val totalItems: Int,
        val items: List<Int>
)

data class FileObj(
        val localIdentifier: String,
        val contentIdentifier: String,
        val filename: String,
        val filesize: Long
)

data class FileResponse(
        val id: String,
        val contentIdentifier: String,
        val localIdentifier: String,
        val meta: Meta,
        val name: String,
        val size: Int,
        val uploadId: String,
        val uploadExpiresAt: Int
)

data class Meta(val multipartParts: Int, val multipartUploadId: String)

data class PartUrl(
        val uploadUrl: String,
        val partNumber: Int,
        val uploadId: String,
        val uploadExpiresAt: Int
)