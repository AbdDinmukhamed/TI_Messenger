package com.example.ti_messenger.data

import android.util.Log

object ScrollPositions {
    val scrollPositions = mutableMapOf<Int,Int>()

    fun getPosition(id: Int, messages: List<Message>): Int{
        val savedValue = scrollPositions[id]
        if (savedValue == null){
            scrollPositions[id] = messages.size
        }
        return scrollPositions[id]!!
    }

}