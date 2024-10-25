package com.letianpai.robot.components.parser.filetoken

class PhotoPushData {
    var photo_id: Int = 0
    lateinit var xyxy: IntArray
    var conf: Float = 0f
    var cls: Int = 0

    override fun toString(): String {
        return "{" +
                "photo_id:" + photo_id +
                ", xyxy:" + xyxy.contentToString() +
                ", conf:" + conf +
                ", cls:" + cls +
                '}'
    }
}
