package fr.sae_graphes.main;

import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class UnePetiteFamille {
    
    public static Set<String> famille(Graph<String, DefaultEdge> graphe, String acteur) 
            throws PasDeTelActeurException {
    
        if (!graphe.containsVertex(acteur)) {
            throw new PasDeTelActeurException();
        }
        Set<String> famille = new HashSet<>();
        LinkedList<String> aVisiter = new LinkedList<>();
        aVisiter.add(acteur);
        famille.add(acteur);
        
        while (!aVisiter.isEmpty()) {
            String actuelActeur = aVisiter.removeFirst();
            Set<String> voisins = CollaborateursEnCommun.getVoisins(graphe, actuelActeur);
            
            for (String voisin : voisins) {
                if (!famille.contains(voisin)) {
                    famille.add(voisin);
                    aVisiter.addLast(voisin);
                }
            }
        }
        return famille;
    }
}