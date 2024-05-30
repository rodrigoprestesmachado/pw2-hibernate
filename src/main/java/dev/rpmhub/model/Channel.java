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

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * Represents a channel in the application.
 */
@Entity
@Setter
@Getter
public class Channel extends PanacheEntity {

    /** The hash of the channel. */
    private String hash;

    /** The list of users in the channel. */
    @ManyToMany(mappedBy = "channels", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<User> users;

    /**
     * Creates a new instance of the Channel class.
     * Initializes the list of users and generates a random hash.
     */
    public Channel() {
        this.users = new ArrayList<>();
        this.hash = UUID.randomUUID().toString();
    }

    /**
     * Adds a user to the channel.
     * @param user The user to add.
     */
    public void addUser(final User user) {
        this.users.add(user);
    }
}
