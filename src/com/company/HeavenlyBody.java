package com.company;

import java.util.HashSet;
import java.util.Set;

//cannot be an immutable class because we keep discovering things to add/change
public abstract class HeavenlyBody {
    private final Key key;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;

    //one way
    /*public static final int STAR = 1;
    public static final int PLANET = 2;
    public static final int DWARF_PLANET = 3;
    public static final int MOON = 4;
    public static final int COMET = 5;
    public static final int ASTEROID = 6;*/

    //better way [Using Enums]
    public enum BodyTypes{
        STAR,
        PLANET,
        DWARF_PLANET,
        MOON,
        COMET,
        ASTEROID
    }

    public HeavenlyBody(String name, double orbitalPeriod, BodyTypes bodyType){
        this.key = new Key(name, bodyType);
        this.orbitalPeriod = orbitalPeriod;
        this.satellites = new HashSet<>();
    }

    public double getOrbitalPeriod(){
        return this.orbitalPeriod;
    }

    public Key getKey(){
        return this.key;
    }

    public Set<HeavenlyBody> getSatellites(){
        //creating new HashMap of the current contents of this.satellites
        return new HashSet<>(this.satellites); //this prevents any changes to this.satellites from other classes
    }

    public boolean addSatellite(HeavenlyBody moon){
        return this.satellites.add(moon);
    }

    //final to avoid problems with asymmetry
    @Override
    public final boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if(obj instanceof HeavenlyBody){
            HeavenlyBody theObject = (HeavenlyBody) obj;
            return this.key.equals(theObject.getKey());
        }

        return false;
    }

    @Override
    public final int hashCode() {
        return this.key.hashCode();
    }

    public static Key makeKey(String name, BodyTypes bodyType){
        return new Key(name, bodyType);
    }

    @Override
    public String toString(){
        return this.key.name + ": " + this.key.bodyType + ", " + this.orbitalPeriod;
    }

     public static final class Key {
        private String name;
        private BodyTypes bodyType;

        private Key(String name, BodyTypes bodyType){
            this.name = name;
            this.bodyType = bodyType;
        }

        public String getName(){
            return name;
        }

        public BodyTypes getBodyType(){
            return bodyType;
        }

         @Override
         public int hashCode() {
             return this.name.hashCode() + 57 + this.bodyType.hashCode();
         }

         @Override
         public boolean equals(Object obj) {
             Key key = (Key) obj;
             if(this.name.equals(key.getName())){
                 return(this.bodyType == key.getBodyType());
             }
             return false;
         }

         @Override
         public String toString(){
            return this.name + ": " + this.bodyType;
         }
     }
}
