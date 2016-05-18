package test.attachment;

import javax.persistence.*;

@Entity
@Table(name = "attachment")
public class Content {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    @Lob
    private byte[] content;

    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    private Attachment attachment;

    public Content() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}
