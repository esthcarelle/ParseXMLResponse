package com.catchy.xmlParser.model

import android.os.Parcel
import android.os.Parcelable
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class RssFeed(
    @field:Element(name = "channel")
    var channel: Channel?
)
{
    constructor() : this(null)
}
@Root(name = "channel", strict = false)
data class Channel(
    @field:ElementList(name = "item", inline = true)
    var items: List<NewItem>?
)
{
    constructor() : this(null)
}
@Root(name = "item", strict = false)
data class NewItem(
    @field:Element(name = "title")
    var title: String?,

    @field:Element(name = "link")
    var link: String?,

    @field:Element(name = "description")
    var description: String?,

    @field:Element(name = "pubDate")
    var pubDate: String?
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    constructor() : this("", "", "","")

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<NewItem> {
        override fun createFromParcel(parcel: Parcel): NewItem {
            return NewItem(parcel)
        }

        override fun newArray(size: Int): Array<NewItem?> {
            return arrayOfNulls(size)
        }
    }
}

