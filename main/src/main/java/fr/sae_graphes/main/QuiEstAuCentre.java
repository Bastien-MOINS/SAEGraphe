package fr.sae_graphes.main;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.BFSShortestPath;
import org.jgrapht.graph.DefaultEdge;
import java.lang.Math;

public class QuiEstAuCentre {
    public static int centraliteDUnActeur(Graph<String, DefaultEdge> graphe, String v) {
        BFSShortestPath<String, DefaultEdge> parcourLargeur = new BFSShortestPath<>(graphe);
        int centralite = 0;

        for (String acteur : graphe.vertexSet()) {
            if (!acteur.equals(v)) {
                // Calcule le plus court chemin entre v et un autre acteur
                org.jgrapht.GraphPath<String, DefaultEdge> path = parcourLargeur.getPath(v, acteur);

                if (path == null) {
                    //Si non connexe
                    return Integer.MAX_VALUE;
                }

                int distance = path.getLength();
                centralite = Math.max(centralite, distance);

                }
            }
        return centralite;
    }

    public static String centreDuGraphe(Graph<String, DefaultEdge> graphe) {
        String centre = null;
        int minCentralite = Integer.MAX_VALUE;

        for (String v : graphe.vertexSet()) {
            int c = centraliteDUnActeur(graphe, v);
            if (c < minCentralite) {
                minCentralite = c;
                centre = v;
            }
        }

        return centre;
    }
}