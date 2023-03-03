package com.example.sumdu_kotlin_datacollector
/**
 * Data Structure that save the instance of data
 * and all of the children for it
 **/
data class Group(val name: String) {
    val children = mutableListOf<String>()

    fun addChild(child: String) {
        children.add(child)
    }
}