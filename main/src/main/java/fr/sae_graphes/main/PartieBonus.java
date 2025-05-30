package fr.sae_graphes.main;

import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;


public class PartieBonus {
    
    /**
     * Trouve les acteurs les plus connectés (centraux)
     */
    public static List<String> acteursCentraux(Graph<String, DefaultEdge> graphe, Integer nombreActeurs) {
        
        // Je crée une map pour compter les connections de chaque acteur
        Map<String, Integer> compteurConnections = new HashMap<>();
        
        // Pour chaque acteur, je compte ses voisins
        for (String acteur : graphe.vertexSet()) {
            Integer nbVoisins = graphe.edgesOf(acteur).size();
            compteurConnections.put(acteur, nbVoisins);
        }
        
        // Je trie par nombre de connections (du plus grand au plus petit)
        // Je garde seulement les premiers
        return null;
    }
    
    //Trouve les collaborateurs communs entre deux acteurs

}