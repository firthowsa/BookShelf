package com.example.bookshelf.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Book(
    val kind: String,
    val id:String,
    val etag:String,
    val selfLink:String,
    val volumeInfo:VolumeInfo,
    val saleInfo: SaleInfo,
    val accessInfo: info,
    val searchInfo:text

)
@Serializable
data class text(

    val textSnippet: String,


)

@Serializable
data class info(
    val country: String,
    val viewability: String,
    val embeddable: Boolean,
    val publicDomain: Boolean,
    val textToSpeechPermission: String,
    val epub:available,
    val pdf:available,
    val webReaderLink: String,
    val accessViewStatus: String,
    val quoteSharingAllowed: Boolean

)

@Serializable
data class available(

    val isAvailable: Boolean,
    val acsTokenLink: String? =null

)

@Serializable
data class SaleInfo(
    val country: String,
    val isEbook: Boolean,
    val saleability: String
)
@Serializable
data class ListPrice(
    val amount: Float?,
    val currency: String? = ""
)
@Serializable
data class identifier(
    val type:String,
    val identifier:String
)
@Serializable
data class VolumeInfo(
    val title: String,
    val subtitle: String? = null,
    val description: String? =null,
    val imageLinks: ImageLink? = null,
    val authors: List<String>,
    val publisher: String? = null,
    val publishedDate: String,
    val industryIdentifiers:List<identifier>,
    val readingModes:readingMode,
    val pageCount: Int? = null,
    val printedPageCount: Int? = null,
    //val dimensions: Int? = null,
    val printType: String,
    val categories:List<String>? = null,
    val averageRating: Double? =null,
    val ratingsCount: Int? = null,
    val maturityRating: String,
    val allowAnonLogging: Boolean,
    val contentVersion: String,
    val panelizationSummary: bubble,
    val language:String,
    val previewLink: String,
    val infoLink:String,
    val canonicalVolumeLink:String,



)

@Serializable
data class bubble(
    val containsEpubBubbles:Boolean,
    val containsImageBubbles:Boolean
)
@Serializable
data class readingMode(
    val text:Boolean,
    val image:Boolean
)
@Serializable
data class ImageLink(
    val smallThumbnail: String,
    val thumbnail: String,
){
    val httpsThumbnail : String
        get() = thumbnail.replace("http", "https")
}


