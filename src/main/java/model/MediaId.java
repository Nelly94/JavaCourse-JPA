package model;

import java.io.Serializable;

public class MediaId implements Serializable {

    public String name;

    public MediaType type;

    public MediaId(String name, MediaType type) {
        this.name = name;
        this.type = type;
    }
}
