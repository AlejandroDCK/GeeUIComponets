package com.letianpai.robot.components.parser.filetoken

class CloudFileTokenData {
    var upload_domain: String? = null
    var upload_token: String? = null
    var download_url: String? = null
    var file_key: String? = null

    override fun toString(): String {
        return "{" +
                "upload_domain:'" + upload_domain + '\'' +
                ", upload_token:'" + upload_token + '\'' +
                ", download_url:'" + download_url + '\'' +
                ", file_key:'" + file_key + '\'' +
                '}'
    }
}
