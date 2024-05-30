/**
 * PW2 by Rodrigo Prestes Machado
 *
 * PW2 is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
*/
package dev.rpmhub.ws;

import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import dev.rpmhub.model.User;

// 1 - Podemos delegar o controle de transação utilizando a anotação
// @Transactional nos métodos ou no nível da classe.
/**
 * This class represents a RESTful web service for managing user data.
 * It provides endpoints for saving, listing, and retrieving user information.
 */
@Path("/user")
@Transactional
public class UserWS {

    /**
     * Salva um usuário.
     *
     * @param name O nome do usuário.
     * @return O usuário salvo.
     */
    @GET
    @Path("/save/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public User save(@PathParam("name") final String name) {
        User user = new User();
        user.setName(name);
        // 2 - O método do Panache `persist` possibilita persistir um objeto.
        user.persist();
        return user;
    }

    /**
     * Lista todos os usuários.
     *
     * @return A lista de usuários.
     */
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<User> list() {
        // 3 - O método `listAll` recupera todos os objetos da classe User.
        return User.listAll();
    }

    /**
     * Recupera um usuário pelo ID.
     *
     * @param id O ID do usuário.
     * @return O usuário recuperado.
     */
    @GET
    @Path("/list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User list(@PathParam("id") final Long id) {
        // 4 - O método do Panache `findById` recupera um objeto da classe User.
        return User.findById(id);
    }

}
