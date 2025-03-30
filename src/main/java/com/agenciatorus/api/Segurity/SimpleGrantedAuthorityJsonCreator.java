package com.agenciatorus.api.Segurity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public abstract  class SimpleGrantedAuthorityJsonCreator {

   @JsonCreator
   public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role){}
}
