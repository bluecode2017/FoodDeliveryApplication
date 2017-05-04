package com.sarah.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;


/**
 * Created by vagrant on 5/2/17.
 */
@Table(name = "restaurants")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int restaurantId;
    private String restaurantName;
    private String restaurantDesc;
    private String restaurantAddress;

    //@OneToMany(mappedBy = "restaurants")
    //private List<MenuItem> menu = new ArrayList<MenuItem>();

    public Restaurant() {

    }

    @JsonCreator
    public Restaurant(@JsonProperty("name") String restaurantName,
                      @JsonProperty("description") String restaurantDesc,
                      @JsonProperty("address") String restaurantAddress) {
        this.restaurantName = restaurantName;
        this.restaurantDesc = restaurantDesc;
        this.restaurantAddress = restaurantAddress;
    }

    /*
    public void addMenuItem(MenuItem menuItem){
        menu.add(menuItem);
    }
    */

    /*
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "Restaurant")
    public List<MenuItem> getMenu(){
        return menu;
    }
    public void setMenu(List<MenuItem> menu){
        this.menu = menu;
    }
*/

}
