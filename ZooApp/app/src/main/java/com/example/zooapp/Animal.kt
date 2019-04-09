package com.example.zooapp

class Animal{

    var name:String?=null
    var des:String?=null
    var image:Int?=null
    var isKilled:Boolean?=null
    constructor(name:String, des:String, image:Int, isKilled:Boolean){
        this.name=name
        this.des=des
        this.image=image
        this.isKilled=isKilled
    }

}