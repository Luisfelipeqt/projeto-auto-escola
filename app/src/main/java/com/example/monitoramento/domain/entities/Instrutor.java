package com.example.monitoramento.domain.entities;


import com.example.monitoramento.domain.entities.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_instrutores")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(SnakeCaseStrategy.class)
public class Instrutor extends Entidade {

    @Column(nullable = false, unique = true, length = 15)
    private String cpf;

    @Column(nullable = false, unique = false, length = 100)
    private String primeiroNome;

    @Column(nullable = false, unique = false, length = 100)
    private String sobreNome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = false, length = 15)
    private Sexo sexo = Sexo.OUTRO;

    @ElementCollection
    @CollectionTable(name="tab_instrutor_email",
            joinColumns=@JoinColumn(name = "instrutor_id", referencedColumnName = "cpf"))
    @Column(name="emails", unique = true, length = 50)
    private Set<String> emails;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "instrutor_cpf",referencedColumnName = "cpf")
    private Set<Telefone> telefones;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @ManyToMany(mappedBy = "instrutores")
    private Set<Cfc> cfcs = new HashSet<>();

    @Embedded
    private Endereco endereco;
}
