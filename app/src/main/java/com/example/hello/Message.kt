package com.example.hello

class Message {
    var message: String?=null
    var sendId: String?=null

    constructor(){}

    constructor(message: String?, sendId: String?) {
        this.message = message
        this.sendId = sendId
    }
}