package dev.bstk.exportadorapipdf.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "CONTEUDO_PDF")
public class ConteudoPdf<T> implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "DATA_INSERT")
    private LocalDateTime dataInsert;

    @NotNull
    @Column(name = "DATA_UPDATE")
    private LocalDateTime dataUpdate;

    @NotNull
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private ConteudoPdfStatus status;

    @Column(columnDefinition = "jsonb")
    @Convert(converter = ConteudoPdfConverter.class)
    private T dados;

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

    public T getDados() {
        return dados;
    }

    public void setDados(T dados) {
        this.dados = dados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        ConteudoPdf<?> that = (ConteudoPdf<?>) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
