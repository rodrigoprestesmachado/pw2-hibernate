/**
 * PW2 by Rodrigo Prestes Machado
 *
 * PW2 is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
 *
*/
package dev.rpmhub.ws;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import dev.rpmhub.model.Message;
import dev.rpmhub.model.User;

/**
 * This class represents a RESTful web service for handling messages.
 */
@Path("/message")
@Transactional
public class MessageWS {

  /**
   * Saves a message.
   * @param text The text of the message.
   * @param idUser The ID of the user.
   * @return The saved message.
   */
   @GET
   @Path("/save/{text}/{idUser}")
   @Produces(MediaType.APPLICATION_JSON)
   public Message save(@PathParam("text") final String text,
    @PathParam("idUser") final Long idUser) {

      Message message = new Message();
      message.setText(text);
      message.persistAndFlush();

      User user = User.findById(idUser);
      if (user == null) {
        throw new BadRequestException("User not found");
      }

      user.addMessage(message);
      user.persistAndFlush();

      return message;
   }
}
