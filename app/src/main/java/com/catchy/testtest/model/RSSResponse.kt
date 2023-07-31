package com.catchy.testtest.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class RssFeed(
    @field:ElementList(name = "channel", inline = true)
    var channel: Channel? = null
)

data class Author( @field:ElementList(name = "name", inline = true)
                   val name: String)

data class Contributor( @field:ElementList(name = "name", inline = true)
                        val name: String)

data class Channel(
    @field:ElementList(name = "item", inline = true)
    var items: List<RssItem>? = null
)

data class RssItem(
    @field:Element(name = "title")
    var title: String? = null,
    @field:Element(name = "link")
    var link: String? = null,
    @field:Element(name = "description")
    var description: String? = null
)
