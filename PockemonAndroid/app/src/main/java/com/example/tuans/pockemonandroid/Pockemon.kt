package com.example.tuans.pockemonandroid

import android.location.Location

class Pockemon{
    var name:String?=null
    var des:String?=null
    var image:Int?=null
    var power:Double?=null
    var isCatch:Boolean?=false
    var location:Location?=null
    constructor(image:Int,name:String,des:String,power:Double,lat:Double,long:Double){
        this.name=name
        this.image=image
        this.des=des
        this.power=power
        this.location=Location(name)
        this.location!!.latitude=lat
        this.location!!.longitude=long
        this.isCatch=false
    }
}