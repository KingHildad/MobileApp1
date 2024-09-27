package com.example.mobileapp.models

class Blog {
    var imageUrl: String=""
    var firstname: String=""
    var desc: String=""
    var id: String=""

    constructor(imageUrl: String,firstname:String,desc: String,id: String){
        this.imageUrl=imageUrl
        this.firstname=firstname
        this.desc=desc
        this.id=id
    }
    constructor()

}