package com.example.startup

class Note{
    var noteID: Int?=null
    var noteName: String?=null
    var noteDes: String?=null
    constructor()
    constructor(noteID: Int?, noteName: String?, noteDes: String?) {
        this.noteID = noteID
        this.noteName = noteName
        this.noteDes = noteDes
    }

}