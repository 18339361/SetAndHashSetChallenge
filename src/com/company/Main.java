package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    private static Map<HeavenlyBody.Key, HeavenlyBody> solarSystem = new HashMap<>();
    private static Set<HeavenlyBody> planets = new HashSet<>();

    public static void main(String[] args) {
        //adding planets
        HeavenlyBody temp = new Planet("Mercury", 88);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        temp = new Planet("Venus", 225);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        temp = new Planet("Earth", 365);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        //adding moon
        HeavenlyBody tempMoon = new Moon("Moon", 27);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSatellite(tempMoon); //adding the moon to Earth

        //adding more planets and some of their moons
        temp = new Planet("Mars", 687);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        tempMoon = new Moon("Deimos", 1.3);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSatellite(tempMoon); // temp is still Mars

        tempMoon = new Moon("Phobos", 0.3);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSatellite(tempMoon); // temp is still Mars

        temp = new Planet("Jupiter", 4332);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        tempMoon = new Moon("Io", 1.8);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSatellite(tempMoon); // temp is still Jupiter

        tempMoon = new Moon("Europa", 3.5);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSatellite(tempMoon); // temp is still Jupiter

        tempMoon = new Moon("Ganymede", 7.1);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSatellite(tempMoon); // temp is still Jupiter

        tempMoon = new Moon("Callisto", 16.7);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSatellite(tempMoon); // temp is still Jupiter

        temp = new Planet("Saturn", 10759);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        temp = new Planet("Uranus", 30660);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        temp = new Planet("Neptune", 165);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        temp = new Planet("Pluto", 248);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp); //debatable

        System.out.println("Planets" + "\n*******");
        for(HeavenlyBody planet: planets){
            System.out.println(planet.getKey());
        }

        HeavenlyBody body = solarSystem.get(HeavenlyBody.makeKey("Mars", HeavenlyBody.BodyTypes.PLANET)); //accessing Jupiter
        System.out.println("Moons of " + body.getKey() +"\n****************");
        for(HeavenlyBody planetMoons: body.getSatellites()){
            System.out.println(planetMoons.getKey());
        }

        //getting the union of sets {SETS DO NOT CONTAIN DUPLICATES}
        Set<HeavenlyBody> moons = new HashSet<>();
        for(HeavenlyBody planet : planets){
            moons.addAll(planet.getSatellites());
        }

        System.out.println("All moons" + "\n*********");
        for(HeavenlyBody moon : moons){
            System.out.println(moon.getKey());
        }

        //this is successfully added because the two plutos aren't equal
        //equals() method needs to be overridden to solve this
        //after we @override equals(), only one pluto remains and duplicate is not added
        //only the original pluto remains
        HeavenlyBody pluto = new DwarfPlanet("Pluto", 842);
        planets.add(pluto);

        for(HeavenlyBody planet : planets){
            System.out.println(planet); //overidden toString() method called
//            System.out.println(planet.getKey() + ": " + planet.getOrbitalPeriod());
        }

        //checking to see equals is symmetric [a == b and b == a both return the same boolean]
        HeavenlyBody earth1 = new Planet("Earth", 365);
        HeavenlyBody earth2 = new Planet("Earth", 365);
        System.out.println(earth1.equals(earth2));
        System.out.println(earth2.equals(earth1));

        //both plutos added
        solarSystem.put(pluto.getKey(), pluto);
        System.out.println(solarSystem.get(HeavenlyBody.makeKey("Pluto", HeavenlyBody.BodyTypes.PLANET)));
        System.out.println(solarSystem.get(HeavenlyBody.makeKey("Pluto", HeavenlyBody.BodyTypes.DWARF_PLANET)));

    }
}
