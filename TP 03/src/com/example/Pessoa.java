package com.example;

public class Pessoa {

    private static int kp = 0;
    private String nome;
    private char sexo;
    private int idade;

    public Pessoa() {
        setKp();
    }

    public Pessoa(String nome, char sexo, int idade) {
        this.nome = nome;
        this.sexo = Character.toUpperCase(sexo);
        this.idade = idade;
        setKp();
    }

    public static void setKp() {
        Pessoa.kp++;
    }

    public static int getKp() {
        return kp;
    }

    public String getNome() {
        return nome;
    }

    public char getSexo() {
        return sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSexo(char sexo) {
        this.sexo = Character.toUpperCase(sexo);
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Contador KP: " + getKp() + "\n"
             + "Nome: " + nome + "\n"
             + "Sexo: " + sexo + "\n"
             + "Idade: " + idade + " anos.";
    }
}
