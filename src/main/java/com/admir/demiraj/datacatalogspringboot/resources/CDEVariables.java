/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admir.demiraj.datacatalogspringboot.resources;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author root
 */
@Entity
@Table(name="CDEVariables")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
public class CDEVariables{

    public CDEVariables(@NotBlank String name, @NotBlank String csvFile, String value, String type, String unit,
                        String canBeNull, String description, String comments, String conceptPath, String label) {
        this.name = name;
        this.csvFile = csvFile;
        this.value = value;
        this.type = type;
        this.unit = unit;
        this.canBeNull = canBeNull;
        this.description = description;
        this.comments = comments;
        this.conceptPath = conceptPath;
        this.label = label;
    }

    public CDEVariables() {
    }
    
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long cdevariable_id;

    @NotBlank
    private String name;

    @NotBlank
    private String csvFile;

    @Column
    private String value;

    @Column
    private String type;

    @Column
    private String unit;

    @Column
    private String canBeNull;

    @Column
    private String description;

    @Column
    private String comments;

    @Column
    private String conceptPath;

    @Column
    private String label;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY,cascade =  {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "cdevariables_versions",joinColumns = { @JoinColumn(name = "cdevariable_id") },inverseJoinColumns = { @JoinColumn(name = "version_id") })
    private Set<Versions> versions = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "function_id", nullable = false)
    private Functions function;

    public Functions getFunction() {
        return function;
    }

    public void setFunction(Functions function) {
        this.function = function;
    }

    public String getConceptPath() {
        return conceptPath;
    }

    public void setConceptPath(String conceptPath) {
        this.conceptPath = conceptPath;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getCdevariable_id() {
        return cdevariable_id;
    }

    public void setCdevariable_id(Long cdevariable_id) {
        this.cdevariable_id = cdevariable_id;
    }

    public Set<Versions> getVersions() {
        return versions;
    }

    public void setVersions(Versions versions) {
        this.versions.add(versions);
    }

    public String getCsvFile() {
        return csvFile;
    }

    public void setCsvFile(String csvFile) {
        this.csvFile = csvFile;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCanBeNull() {
        return canBeNull;
    }

    public void setCanBeNull(String canBeNull) {
        this.canBeNull = canBeNull;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setVersions(Set<Versions> versions) {
        this.versions = versions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
