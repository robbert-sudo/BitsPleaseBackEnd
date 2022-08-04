package bitspleaseApp.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    @Column(nullable = false,unique = true)
    public String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(
            targetEntity = Game.class,
            mappedBy = "uploader",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Game> games = new HashSet<>();

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "user_id",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    public String getNameAndEmail() {
        return this.getUsername() + " " + this.getEmail();
    }

    public User() {

    }
    public User(String username, String password, String email ) {
        this.username = username;
        this.password = password;
        this.email = email;
    }




    public long getUser_id() {return user_id;}
    public void setUser_id(long user_id) {this.user_id = user_id;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public boolean isEnabled() {return enabled;}
    public void setEnabled(boolean enabled) {this.enabled = enabled;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public Set<Authority> getAuthorities() {return authorities;}
    public void setAuthorities(Set<Authority> authorities) {this.authorities = authorities;}

    public void addAuthority(Authority authority) {this.authorities.add(authority);}

    public void addAuthority(String authorityString) {
        this.authorities.add(new Authority(this.user_id, authorityString, this.username));
    }

    public Set<Game> getGames() {return games;}

    public void setGames(Set<Game> games) {this.games = games;}

    public void addGame(Game game) {this.games.add(game);}

}
