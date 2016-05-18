package test.attachment;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "attachment")
public class Attachment  {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private long size;

//    @OneToOne(optional = false, fetch=FetchType.LAZY, mappedBy="attachment", cascade = CascadeType.ALL)
//    private Content content;

    @Column(name = "content")
    @Lob
    @Basic(fetch = FetchType.LAZY)
   // @Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
    private byte[] content;

    @Column(name = "type")
    private String type;

    public Attachment() {
    }

    @Override
    public int hashCode() {
        return (getClass().hashCode() + Integer.valueOf(id).hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null && getClass() == obj.getClass())
                ? (id == ((Attachment) obj).id)
                : (obj == this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

//    public Content getContent() {
//        return content;
//    }
//
//    public void setContent(Content content) {
//        this.content = content;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
