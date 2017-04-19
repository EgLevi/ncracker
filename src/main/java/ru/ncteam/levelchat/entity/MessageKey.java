package ru.ncteam.levelchat.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MessageKey implements Serializable {

    static final long serialVersionUID = 1L;

    @Column(name = "id_mess")
    private int id_mess;

    @Column(name = "\"id\"")
    private int id;

    public int getId_mess() {
        return id_mess;
    }

    public void setId_mess(int id_mess) {
        this.id_mess = id_mess;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int hash_code() {
        return this.hashCode();
    }

    public boolean equals(Object o) {
        if (o instanceof MessageKey) {
            MessageKey mKey = (MessageKey) o;
            if (this.getId() == mKey.getId()) {
                if (this.getId_mess() == mKey.getId_mess()) {
                    return true;
                }
            }
        }
        return false;
    }


}
