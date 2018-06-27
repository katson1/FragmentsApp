package editor.katson.comsapp;

import java.io.Serializable;

/**
 * Created by Xoi on 29/06/2017.
 */

public class UserObject implements Serializable{
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String end;
    private String complemento;
    private boolean nomeBoolean;
    private boolean cpfBoolean;
    private boolean telefoneBoolean;
    private boolean emailBoolean;
    private boolean endBoolean;
    private boolean complementoBoolean;

    public UserObject(String nome, String cpf, String telefone, String email, String end,
                      String complemento, boolean nomeBoolean, boolean cpfBoolean, boolean telefoneBoolean,
                      boolean emailBoolean, boolean endBoolean, boolean complementoBoolean) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.end = end;
        this.complemento = complemento;
        this.nomeBoolean = nomeBoolean;
        this.cpfBoolean = cpfBoolean;
        this.telefoneBoolean = telefoneBoolean;
        this.emailBoolean = emailBoolean;
        this.endBoolean = endBoolean;
        this.complementoBoolean = complementoBoolean;



    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public boolean isNomeBoolean() {
        return nomeBoolean;
    }

    public void setNomeBoolean(boolean nomeBoolean) {
        this.nomeBoolean = nomeBoolean;
    }

    public boolean isCpfBoolean() {
        return cpfBoolean;
    }

    public void setCpfBoolean(boolean cpfBoolean) {
        this.cpfBoolean = cpfBoolean;
    }

    public boolean isTelefoneBoolean() {
        return telefoneBoolean;
    }

    public void setTelefoneBoolean(boolean telefoneBoolean) {
        this.telefoneBoolean = telefoneBoolean;
    }

    public boolean isEmailBoolean() {
        return emailBoolean;
    }

    public void setEmailBoolean(boolean emailBoolean) {
        this.emailBoolean = emailBoolean;
    }

    public boolean isEndBoolean() {
        return endBoolean;
    }

    public void setEndBoolean(boolean endBoolean) {
        this.endBoolean = endBoolean;
    }

    public boolean isComplementoBoolean() {
        return complementoBoolean;
    }

    public void setComplementoBoolean(boolean complementoBoolean) {
        this.complementoBoolean = complementoBoolean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserObject)) return false;

        UserObject usuario = (UserObject) o;

        return cpf.equals(usuario.cpf);

    }

    @Override
    public int hashCode() {
        return cpf.hashCode();
    }


}
