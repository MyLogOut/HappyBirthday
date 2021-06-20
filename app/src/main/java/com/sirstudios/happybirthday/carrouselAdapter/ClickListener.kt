package com.sirstudios.happybirthday.carrouselAdapter

interface ClickListener<in T> {
    fun itemClicked(item: T)
}