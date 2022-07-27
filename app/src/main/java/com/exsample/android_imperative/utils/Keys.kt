package com.exsample.android_imperative.utils

object Keys {
    init{
        System.loadLibrary("keys")
    }
    external fun getPrivateKey(): String
    external fun getPublicKey(): String
}