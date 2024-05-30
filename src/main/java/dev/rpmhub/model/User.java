/**
 * PW2 by Rodrigo Prestes Machado
 *
 * PW2 is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
 *
*/
package dev.rpmhub.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * Represents a user in the system.
 */
@Entity
@Setter @Getter
public class User extends PanacheEntity {

    /** The name of the user. */
    private String name;

    /** A hash to identify the user. */
    private String hash;

    /** The list of messages the user has. */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // name = nome da coluna que irá armazenar a chave estrangeira
    // na tabela Message (solução a partir da JPA 2)
    @JoinColumn(name = "user_id")
    private List<Message> messages;

    /** The list of channels the user is in. */
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Channel> channels;

    /**
     * Creates a new instance of the User class.
     */
    public User() {
        this.messages = new ArrayList<>();
        this.channels = new ArrayList<>();
        this.hash = UUID.randomUUID().toString();
    }

    /**
     * Adds a message to the user.
     * @param message The message to add.
     */
    public void addMessage(final Message message) {
        this.messages.add(message);
    }

    /**
     * Adds a channel to the user.
     * @param channel The channel to add.
     */
    public void addChannel(final Channel channel) {
        this.channels.add(channel);
    }

}
