package com.example.monitoramento.domain.entities;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_cfc")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(SnakeCaseStrategy.class)
public class Cfc extends Entidade {


    @Column(nullable = false, unique = true, length = 20)
    private String cnpj;

    @Column(nullable = false, length = 255)
    private String nomeFantasia;

    @ElementCollection
    @CollectionTable(name = "tab_cfc_email",
            joinColumns = @JoinColumn(name = "cfc_cnpj", referencedColumnName = "cnpj"))
    @Column(name = "emails", nullable = false)
    private Set<String> emails;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "cfc_cnpj", referencedColumnName = "cnpj")
    @Column(nullable = false)
    private Set<Telefone> telefones;

    @Embedded
    private Endereco endereco;

    @ManyToMany
    @JoinTable(name = "cfc_instrutores"
            , joinColumns = @JoinColumn(name = "tb_cfc_cnpj")
            , inverseJoinColumns = @JoinColumn(name = "tb_instrutor_cpf"))
    private Set<Instrutor> instrutores = new HashSet<>();
}