package com.catchy.testtest.model

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
    var items: List<NewsItem>?
)
{
    constructor() : this(null)
}
@Root(name = "item", strict = false)
data class NewsItem(
    @field:Element(name = "title")
    var title: String,

    @field:Element(name = "link")
    var link: String,

    @field:Element(name = "description")
    var description: String,

    @field:Element(name = "pubDate")
    var pubDate: String
){
    constructor() : this("", "", "","")
}

