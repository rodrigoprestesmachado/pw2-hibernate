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

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * Represents a message entity.
 */
@Entity
@Setter
@Getter
public class Message extends PanacheEntity {

    /** The text of the message. */
    private String text;

    /**
     * Gets the ID of the message.
     *
     * @return the ID of the message
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the message.
     *
     * @param id the ID of the message
     */
    public void setId(final Long id) {
        this.id = id;
    }

}
