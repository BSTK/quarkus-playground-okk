package dev.bstk.exportadorapipdf.domain.model;

import dev.bstk.exportadorapipdf.domain.model.genius.GeniusEndpointSearchConteudoPdf;
import io.quarkiverse.hibernate.types.json.JsonBinaryType;
import io.quarkiverse.hibernate.types.json.JsonTypes;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "CONTEUDO_PDF")
@TypeDef(name = JsonTypes.JSON_BIN, typeClass = JsonBinaryType.class)
public class ConteudoPdf implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "DATA_INSERT")
    private LocalDateTime dataInsert;

    @NotNull
    @Column(name = "DATA_UPDATE")
    private LocalDateTime dataUpdate;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private ConteudoPdfStatus status;

    @Type(type = JsonTypes.JSON_BIN)
    @Column(name = "DADOS", columnDefinition = JsonTypes.JSON_BIN)
    private GeniusEndpointSearchConteudoPdf dados;

    @PrePersist
    protected void persist() {
        setDataInsert(LocalDateTime.now());
        setDataUpdate(LocalDateTime.now());
        setStatus(ConteudoPdfStatus.PARA_PROCESSAR);
    }

    @PreUpdate
    protected void update() {
        setDataUpdate(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataInsert() {
        return dataInsert;
    }

    public void setDataInsert(LocalDateTime dataInsert) {
        this.dataInsert = dataInsert;
    }

    public LocalDateTime getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(LocalDateTime dataUpdate) {
        this.dataUpdate = dataUpdate;
    }

    public ConteudoPdfStatus getStatus() {
        return status;
    }

    public void setStatus(ConteudoPdfStatus status) {
        this.status = status;
    }

    public GeniusEndpointSearchConteudoPdf getDados() {
        return dados;
    }

    public void setDados(GeniusEndpointSearchConteudoPdf dados) {
        this.dados = dados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        ConteudoPdf that = (ConteudoPdf) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
