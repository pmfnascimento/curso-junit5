package com.web.java.barriga.infra;

import com.web.java.barriga.dominio.Usuario;
import com.web.java.barriga.repositorie.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioMemoryRepository implements UsuarioRepository {

    private List<Usuario> users;
    private Long currentId;

    public UsuarioMemoryRepository() {
        this.users = new ArrayList<>();
        this.currentId = 0L;
        salvar(new Usuario(null, "User #1", "user@email.com", "123456"));
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        Usuario newUser = new Usuario(nextId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
        users.add(newUser);
        return newUser;
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public void printUsers() {
        System.out.println(users);
    }

    private Long nextId() {
        return ++currentId;
    }

    public static void main(String[] args) {
        UsuarioMemoryRepository repo = new UsuarioMemoryRepository();
        repo.printUsers();
        repo.salvar(new Usuario(null, "Usuário qualquer", "mail@email.com","aaa"));
        repo.printUsers();
    }
}
