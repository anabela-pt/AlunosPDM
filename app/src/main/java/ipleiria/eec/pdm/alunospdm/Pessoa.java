package ipleiria.eec.pdm.alunospdm;

import java.io.Serializable;

public class Pessoa implements Serializable, Comparable<Pessoa> {
    private int numero;
    private String nome;
    private char tipo;

    public Pessoa(int numero, String nome, char tipo) {
        this.numero = numero;
        this.nome = nome;
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public char getTipo() {
        return tipo;
    }

    @Override
    public int compareTo(Pessoa pessoa) {
        return nome.compareToIgnoreCase(pessoa.nome);
    }

    @Override
    public String toString() {
        return "Numero: " + numero + "\nNome: '" + nome;
    }

}
