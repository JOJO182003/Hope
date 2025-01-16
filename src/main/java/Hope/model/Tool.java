package Hope.model;

import jakarta.persistence.*;
/*
@Entity
@Table(name = "tool")*/
@Entity
@Table(name = "tool")
@NamedQueries({
    @NamedQuery(
        name = "Tool.search",
        query = "SELECT t FROM Tool t WHERE " +
                "LOWER(t.titre) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
                "LOWER(t.domaine) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
                "LOWER(t.descriptionSimple) LIKE LOWER(CONCAT('%', :query, '%'))"
    )
})
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "Titre", nullable = false)
    private String titre;

    @Column(name = "Domaine", nullable = false)
    private String domaine;

    @Lob
    @Column(name = "Lien", nullable = false)
    private String lien;

    @Lob
    @Column(name = "Description_simple")
    private String descriptionSimple;

    @Lob
    @Column(name = "Description_detaillee")
    private String descriptionDetaillee;

    @Lob
    @Column(name = "Acces")
    private String acces;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getDescriptionSimple() {
        return descriptionSimple;
    }

    public void setDescriptionSimple(String descriptionSimple) {
        this.descriptionSimple = descriptionSimple;
    }

    public String getDescriptionDetaillee() {
        return descriptionDetaillee;
    }

    public void setDescriptionDetaillee(String descriptionDetaillee) {
        this.descriptionDetaillee = descriptionDetaillee;
    }

    public String getAcces() {
        return acces;
    }

    public void setAcces(String acces) {
        this.acces = acces;
    }

}
