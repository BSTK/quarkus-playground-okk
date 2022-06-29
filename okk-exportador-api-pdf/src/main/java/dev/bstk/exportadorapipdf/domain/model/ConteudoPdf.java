package dev.bstk.exportadorapipdf.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "CONTEUDO_PDF")
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

    @NotNull
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private ConteudoPdfStatus status;
}
