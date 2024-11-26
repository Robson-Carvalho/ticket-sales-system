/***************************
 * Autor: Robson Carvalho de Souza
 * Componente Curricular: MI de Programação
 * Concluído em: 16/09/2024
 * Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 * trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 * apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 * de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 * do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 ******************************/

package com.uefs.system.service;

import com.uefs.system.model.User;
import com.uefs.system.repository.UserRepository;
import com.uefs.system.Interface.IService;

import java.util.List;
import java.util.UUID;

/**
 * Classe de serviço para gerenciar operações relacionadas a usuários.
 * Implementa a interface IService para a entidade User.
 */
public class UserService implements IService<User> {
    private final UserRepository userRepository;

    /**
     * Construtor que inicializa o repositório de usuários.
     */
    public UserService() {
        this.userRepository = new UserRepository();
    }

    /**
     * Cria um novo usuário e o salva no repositório, verificando se o login, email ou CPF já está em uso.
     *
     * @param user Usuário a ser criado.
     * @return O usuário criado.
     * @throws SecurityException Caso login, email ou CPF já estejam em uso.
     */
    @Override
    public User create(User user) throws Exception {
        User u = getByLogin(user.getLogin());

        if(u != null && u.getLogin() != null) {
            throw new SecurityException("SignUp, email e/ou cpf já está em uso.");
        }

        if (this.getByEmail(user.getEmail()) != null || this.getByCpf(user.getCpf()) != null || this.getByEmail(user.getEmail()) != null) {
            throw new SecurityException("SignUp, email e/ou cpf já está em uso.");
        }

        userRepository.save(user);

        return user;
    }

    /**
     * Retorna todos os usuários cadastrados no repositório.
     *
     * @return Lista de todos os usuários.
     */
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Busca um usuário pelo seu ID.
     *
     * @param id ID do usuário a ser buscado.
     * @return Usuário encontrado ou null se não existir.
     */
    @Override
    public User getById(UUID id) {
        return userRepository.findById(id);
    }

    /**
     * Atualiza um usuário existente, verificando se o email já está em uso por outro usuário.
     *
     * @param user Usuário a ser atualizado.
     * @throws Exception Caso o email já esteja em uso por outro usuário.
     */
    @Override
    public void update(User user) throws Exception {
        User _user = this.getByEmail(user.getEmail());

        if (_user != null && !_user.getId().equals(user.getId())) {
            throw new Exception("Email já está em uso.");
        }

        userRepository.update(user);
    }

    /**
     * Deleta um usuário pelo seu ID.
     *
     * @param id ID do usuário a ser deletado.
     */
    @Override
    public void delete(UUID id) {
        userRepository.delete(id);
    }

    /**
     * Deleta todos os usuários do repositório.
     */
    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    /**
     * Busca um usuário pelo seu login.
     *
     * @param login SignUp do usuário a ser buscado.
     * @return Usuário encontrado ou null se não existir.
     */
    public User getByLogin(String login) {
        List<User> users = this.getAll();

        for (User u : users) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }

        return null;
    }

    /**
     * Busca um usuário pelo seu CPF.
     *
     * @param cpf CPF do usuário a ser buscado.
     * @return Usuário encontrado ou null se não existir.
     */
    public User getByCpf(String cpf) {
        List<User> users = this.getAll();

        for (User u : users) {
            if (u.getCpf().equals(cpf)) {
                return u;
            }
        }

        return null;
    }

    /**
     * Busca um usuário pelo seu email.
     *
     * @param email Email do usuário a ser buscado.
     * @return Usuário encontrado ou null se não existir.
     */
    public User getByEmail(String email) {
        List<User> users = this.getAll();

        for (User u : users) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }

        return null;
    }

    /**
     * Verifica se um usuário pode fazer login com o login e senha fornecidos.
     *
     * @param login SignUp do usuário.
     * @param password Senha do usuário.
     * @return true se as credenciais estiverem corretas, false caso contrário.
     */
    public boolean login(String login, String password) {
        User user = this.getByLogin(login);
        return user != null && user.getLogin().equals(login) && user.getPassword().equals(password);
    }
}
