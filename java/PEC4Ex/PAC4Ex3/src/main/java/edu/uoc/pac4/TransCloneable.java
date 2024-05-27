package edu.uoc.pac4;

import edu.uoc.pac4.exception.EntityException;
import edu.uoc.pac4.exception.PositionException;

public interface TransCloneable {
    public Enemy transClone() throws EntityException, PositionException, CloneNotSupportedException;
}
