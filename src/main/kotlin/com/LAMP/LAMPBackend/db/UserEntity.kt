package com.LAMP.LAMPBackend.db

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*


@Entity(name = "lamp_user")
data class UserEntity(@Id
                      @Column(name = "user_id")
                      @GeneratedValue(strategy = GenerationType.AUTO)
                      val id: Int? = null,
                      @Column(nullable = false, unique = true, length = 30)
                      var username: String,
                      var password: String,

                      @ElementCollection(fetch = FetchType.EAGER)
                      var roles: List<String>,
                      var name: String,
                      var email: String) {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    fun isAccountNonExpired(): Boolean {
        return true
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    fun isAccountNonLocked(): Boolean {
        return true
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    fun isCredentialsNonExpired(): Boolean {
        return true
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    fun isEnabled(): Boolean {
        return true
    }
}
