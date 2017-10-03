package ru.iteco.ip.ksm.core.internal.gdbspathexpanders;

import org.neo4j.graphdb.PathExpander;

import java.util.HashMap;

/**
 * Created by Scorpio on 02.10.2017.
 */
public interface CalculateKPIStatePathExpader<T> extends PathExpander<T> {
    void reset();

    PathExpander<T> setAffectedNodes(HashMap<String, String> affectedNodesMap);

}
