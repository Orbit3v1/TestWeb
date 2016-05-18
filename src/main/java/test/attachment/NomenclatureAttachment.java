package test.attachment;

import javax.persistence.*;

@Entity
@Table(name = "nomenclature_attachment")
public class NomenclatureAttachment {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="nomenclature")
    private Nomenclature nomenclature;



    public NomenclatureAttachment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

}
