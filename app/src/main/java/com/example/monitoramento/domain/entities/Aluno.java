package com.example.monitoramento.domain.entities;

import com.example.monitoramento.domain.entities.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_aluno")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Aluno extends Entidade {

    @Column(nullable = false, unique = true, length = 15)
    private String cpf;

    @Column(nullable = false, unique = false, length = 255)
    private String primeiroNome;

    @Column(nullable = false, unique = false, length = 255)
    private String sobreNome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = false, length = 20)
    private Sexo sexo = Sexo.OUTRO;

    @ElementCollection
    @CollectionTable(name = "tab_aluno_email",
            joinColumns = @JoinColumn(name = "aluno_cpf", referencedColumnName = "cpf"))
    @Column(name = "emails", nullable = false)
    private Set<String> emails;

    @Column(nullable = false)
    @JoinColumn(name = "aluno_cpf", referencedColumnName = "cpf")
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<Telefone> telefones;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Embedded
    private Endereco endereco;
}