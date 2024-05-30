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

import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import dev.rpmhub.model.Channel;
import dev.rpmhub.model.User;

@Path("/channel")
@Transactional
public class ChannelWS {

   /**
    * Creates a channel.
    * @param hash The hash of the channel.
    * @return The saved channel.
    */
   @GET
   @Path("/save/{hash}")
   @Produces(MediaType.APPLICATION_JSON)
   public Channel save(@PathParam("hash") final String hash) {
      Channel channel = new Channel();
      channel.setHash(hash);
      channel.persist();
      return channel;
   }

   /**
    * Adds a user to a channel.
    * @param idChannel The ID of the channel.
    * @param idUser The ID of the user.
    * @return The user.
    */
   @GET
   @Path("/add/{idChannel}/{idUser}")
   @Produces(MediaType.APPLICATION_JSON)
   public User add(@PathParam("idChannel") final Long idChannel,
      @PathParam("idUser") final Long idUser) {

      Channel channel = Channel.findById(idChannel);
      if (channel == null) {
        throw new BadRequestException("Channel not found");
      }

      User user = User.findById(idUser);
      if (user == null) {
        throw new BadRequestException("User not found");
      }

      channel.addUser(user);
      user.addChannel(channel);

      user.persist();

      return user;
   }

   /**
    * Lists all channels.
    * @return The list of channels.
    */
   @GET
   @Path("/list")
   @Produces(MediaType.APPLICATION_JSON)
   public List<Channel> list() {
      return Channel.listAll();
   }

}
