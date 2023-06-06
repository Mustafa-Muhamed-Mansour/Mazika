package com.music.mazika.model

class SongModel {
    var title: String = ""
    var subTitle: String = ""
    var songUrl: String = ""
//    var imageUrl: String = ""


    constructor() {

    }

    constructor(title: String, subTitle: String, songUrl: String) {
        this.title = title
        this.subTitle = subTitle
        this.songUrl = songUrl
    }

//    constructor(title: String, subTitle: String, songUrl: String, imageUrl: String) {
//        this.title = title
//        this.subTitle = subTitle
//        this.songUrl = songUrl
//        this.imageUrl = imageUrl
//    }


}

