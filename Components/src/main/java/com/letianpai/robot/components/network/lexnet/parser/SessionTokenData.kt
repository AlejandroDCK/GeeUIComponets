package com.letianpai.robot.components.network.lexnet.parser

class SessionTokenData {
    var access_key: String? = null
    var bucket: String? = null
    var object_key: String? = null
    var object_pre_key: String? = null
    var secret_key: String? = null
    var session_token: String? = null
    var expire_time: Long = 0

    override fun toString(): String {
        return "{" +
                "access_key:'" + access_key + '\'' +
                ", bucket:'" + bucket + '\'' +
                ", object_key:'" + object_key + '\'' +
                ", object_pre_key:'" + object_pre_key + '\'' +
                ", secret_key:'" + secret_key + '\'' +
                ", session_token:'" + session_token + '\'' +
                ", expire_time:" + expire_time +
                '}'
    }
}
