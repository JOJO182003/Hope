package Hope.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tool")
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


/*
@Entity
@Table(name = "tool")
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Le titre est obligatoire.")
    @Size(max = 200, message = "Le titre ne peut pas dépasser 200 caractères.")
    @Column(name = "Titre", nullable = false)
    private String titre;

    @NotBlank(message = "Le domaine est obligatoire.")
    @Size(max = 100, message = "Le domaine ne peut pas dépasser 100 caractères.")
    @Column(name = "Domaine", nullable = false)
    private String domaine;

    @NotBlank(message = "Le lien est obligatoire.")
    @URL(message = "Le lien doit être une URL valide.")
    @Column(name = "Lien", nullable = false)
    private String lien;

    @Size(max = 500, message = "La description simple ne peut pas dépasser 500 caractères.")
    @Column(name = "Description_simple")
    private String descriptionSimple;

    @Size(max = 2000, message = "La description détaillée ne peut pas dépasser 2000 caractères.")
    @Column(name = "Description_detaillee")
    private String descriptionDetaillee;

    @Size(max = 1000, message = "Les informations d'accès ne peuvent pas dépasser 1000 caractères.")
    @Column(name = "Acces")
    private String acces;

    // Getters et Setters
}
*/
