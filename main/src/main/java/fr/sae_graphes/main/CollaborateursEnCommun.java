package fr.sae_graphes.main;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class CollaborateursEnCommun {
    
        public static Set<String> getVoisins(Graph<String, DefaultEdge> graphe, String acteur) {
        Set<String> voisins = new HashSet<>();
        
        for (DefaultEdge edge : graphe.edgesOf(acteur)) {
            String voisin;
            if (graphe.getEdgeSource(edge).equals(acteur)) {
                voisin = graphe.getEdgeTarget(edge);
            } else {
                voisin = graphe.getEdgeSource(edge);
            }
            voisins.add(voisin);
        }
        
        return voisins;
    }


    public static Set<String> enCommun(Graph<String, DefaultEdge> graphe, String acteur1, String acteur2) throws PasDeTelActeurException {

        if (!graphe.containsVertex(acteur1)) {
            System.out.println(acteur1 + "est un illustre inconnu");
            throw new PasDeTelActeurException();
        }
        if (!graphe.containsVertex(acteur2)) {
            System.out.println(acteur2 + "est un illustre inconnu");
            throw new PasDeTelActeurException();

        }

        Set<String> collaborateursActeur1 = getVoisins(graphe, acteur1);
        Set<String> collaborateursActeur2 = getVoisins(graphe, acteur2);
        Set<String> communs = new HashSet<>(collaborateursActeur1);
        communs.retainAll(collaborateursActeur2);
        
        return communs;
    }
    }
