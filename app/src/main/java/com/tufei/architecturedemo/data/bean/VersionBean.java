package com.tufei.architecturedemo.data.bean;

/**
 * @author tufei
 * @date 2017/9/12
 */
public class VersionBean {
    /**
     * {
     "id": 49,
     "version": "1.0",
     "type": null,
     "description": "初始版本",
     "path": "http://www.sb.com/app.apk"
     }
     */

    private int id;
    private String type;
    private String description;
    private String version;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
