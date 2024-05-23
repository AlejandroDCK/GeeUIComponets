package com.letianpai.robot.components.parser.filetoken;

public class CloudFileTokenData {

    private String upload_domain;
    private String upload_token;
    private String download_url;
    private String file_key;

    public String getUpload_domain() {
        return upload_domain;
    }

    public void setUpload_domain(String upload_domain) {
        this.upload_domain = upload_domain;
    }

    public String getUpload_token() {
        return upload_token;
    }

    public void setUpload_token(String upload_token) {
        this.upload_token = upload_token;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getFile_key() {
        return file_key;
    }

    public void setFile_key(String file_key) {
        this.file_key = file_key;
    }

    @Override
    public String toString() {
        return "{" +
                "upload_domain:'" + upload_domain + '\'' +
                ", upload_token:'" + upload_token + '\'' +
                ", download_url:'" + download_url + '\'' +
                ", file_key:'" + file_key + '\'' +
                '}';
    }
}
